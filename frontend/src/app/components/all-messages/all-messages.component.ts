import { Component, OnInit } from '@angular/core';
import { Message } from 'src/app/common/message';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-all-messages',
  templateUrl: './all-messages.component.html',
  styleUrls: ['./all-messages.component.css']
})
export class AllMessagesComponent implements OnInit {
  pageIndex: number;
  pageSize: number;
  messageReadList: Message[];
  messageUnreadList: Message[];

  constructor(private messageService: MessageService) { }

  ngOnInit(): void {
    this.listOfReadMessages();
    this.listOfUnreadMessages();
  }

  listOfReadMessages() {
    this.pageIndex = 0;
    this.pageSize = 10;
    this.messageService.getAllReadMessagesByUser(this.pageIndex, this.pageSize).subscribe(data => this.messageReadList = data.content)
  }

  listOfUnreadMessages() {
    this.pageIndex = 0;
    this.pageSize = 10;
    this.messageService.getAllUnreadMessagesByUser(this.pageIndex, this.pageSize).subscribe(data => this.messageUnreadList = data.content)
  }

  changeStatus(messageId: number) {
    this.messageService.changeMessageStatus(messageId);
  }

}
