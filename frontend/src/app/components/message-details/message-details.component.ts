import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageDetails } from 'src/app/common/message-details';
import { FileServiceService } from 'src/app/services/file-service.service';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-message-details',
  templateUrl: './message-details.component.html',
  styleUrls: ['./message-details.component.css']
})
export class MessageDetailsComponent implements OnInit {

  messageDetails: MessageDetails;

  loggedUserId: number;

  constructor(
    private messageService: MessageService,
    private route: ActivatedRoute,
    private fileService: FileServiceService,
    private datePipe: DatePipe) { }

  ngOnInit(): void {
    let messageId = this.route.snapshot.paramMap.get('messageId');
    this.getMessageDetails(Number(messageId));
    this.loggedUserId = Number(this.getAcctualUserId());
  }

  getMessageDetails(messageId: number) {
    this.messageService.getMessageDetails(messageId).subscribe(data => this.messageDetails = data);
  }

  downloadFile(fileId: number, fileName: string) {
    this.fileService.downloadMessageFile(fileId, fileName);
  }

  transformDate(date) {
    return this.datePipe.transform(date, 'yyyy-MM-dd');
  }

  getAcctualUserId() {
    return localStorage.getItem('user_id');
  }

}
