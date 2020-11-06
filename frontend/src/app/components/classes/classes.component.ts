import { Component, OnInit, Input } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import $ from 'jquery';
import { ActivatedRoute } from '@angular/router';
import { Classes } from 'src/app/common/classes';
import { ClassesService } from 'src/app/services/classes.service';
import { FileServiceService } from 'src/app/services/file-service.service';

@Component({
  selector: 'app-classes',
  templateUrl: './classes.component.html',
  styleUrls: ['./classes.component.css']
})
export class ClassesComponent implements OnInit {
  @Input()
  url: string = "https://www.youtube.com/embed/36YnV9STBqc";
  urlSafe: SafeResourceUrl;

  private serverUrl = 'http://localhost:8080/socket'
  private title = 'WebSockets chat';
  private stompClient;

  actualUserId: number;

  classes: Classes;

  constructor(
    private fileService: FileServiceService,
    private route: ActivatedRoute,
    public sanitizer: DomSanitizer,
    private classesService: ClassesService) { }

  ngOnInit() {
    let classesId = this.route.snapshot.paramMap.get('classesId');
    this.getLessonDetails(Number(classesId));
    this.initializeWebSocketConnection();
    this.actualUserId = Number(this.getAcctualUserId());
  }
  initializeWebSocketConnection() {
    let classesId = this.route.snapshot.paramMap.get('classesId');
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe("/chat/" + classesId, (message) => {
        if (message.body) {
          let obj: ChatMessage = JSON.parse(message.body);
          if (obj.userId == Number(localStorage.getItem('user_id'))) {
            $(".chat").append("<div style='font-weight: bold;' class='message'>" + obj.date + ' - ' + obj.userName + "<br>" + obj.message + "</div>")
          } else {
            $(".chat").append("<div class='message'>" + obj.date + ' - ' + obj.userName + "<br>" + obj.message + "</div>")
          }

        }
      });
    });
  }

  getLessonDetails(classesId: number){
    this.classesService.getClassesDetails(classesId).subscribe(data => {
      this.classes = data;
      // this.url = this.classes.url;
      this.urlSafe = this.sanitizer.bypassSecurityTrustResourceUrl(this.url);
    });
  }

  downloadFile(fileId: number, fileName: string) {
    this.fileService.downloadClassesFile(fileId, fileName);
  }

  sendMessage(message) {
    let classesId = this.route.snapshot.paramMap.get('classesId');
    let userId = Number(this.getAcctualUserId())
    this.stompClient.send("/app/send/message/" + String(classesId) + `/${userId}`, {}, message);
    $('#input').val('');
  }

  getAcctualUserId() {
    return localStorage.getItem('user_id');
  }


}

export interface ChatMessage {
  userId: number;
  userName: string;
  date: string;
  message: string;
}
