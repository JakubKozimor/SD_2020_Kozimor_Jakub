import { Component, OnInit, Input } from '@angular/core';
import { MessageService } from './message.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-classes',
  templateUrl: './classes.component.html',
  styleUrls: ['./classes.component.css']
})
export class ClassesComponent implements OnInit {
  title = 'websocket-frontend';
  @Input()
  url: string = "https://www.youtube.com/embed/36YnV9STBqc";
  urlSafe: SafeResourceUrl;

  @Input()
  chatUrl: string = "https://www.youtube.com/live_chat?v=36YnV9STBqc&embed_domain=localhost";
  chatUrlSafe: SafeResourceUrl;

  constructor(public messageService: MessageService, public sanitizer:DomSanitizer) { }

  ngOnInit() {
    this.urlSafe= this.sanitizer.bypassSecurityTrustResourceUrl(this.url);   
    this.chatUrlSafe= this.sanitizer.bypassSecurityTrustResourceUrl(this.chatUrl);
}

}
