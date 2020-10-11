import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Message } from 'src/app/common/message';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-all-messages',
  templateUrl: './all-messages.component.html',
  styleUrls: ['./all-messages.component.css']
})
export class AllMessagesComponent implements OnInit {
  messageReadList: Message[];
  messageUnreadList: Message[];
  messageSendList: Message[];

  theSendMessagesPageNumber: number = 1;
  theSendMessagesPageSize: number = 5;
  theSendMessagesElements: number = 0;

  theUnreadMessagesPageNumber: number = 1;
  theUnreadMessagesPageSize: number = 5;
  theUnreadMessagesElements: number = 0;

  theReadMessagesPageNumber: number = 1;
  theReadMessagesPageSize: number = 5;
  theReadMessagesElements: number = 0;

  constructor(
    private messageService: MessageService,
    private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.listOfReadMessages();
    this.listOfUnreadMessages();
    this.listOfSendMessages();
  }

  listOfReadMessages() {
    this.messageService.getAllReadMessagesByUser(this.theReadMessagesPageNumber - 1, this.theReadMessagesPageSize).subscribe(data => {
      this.messageReadList = data.content;
      this.theReadMessagesPageNumber = data.number + 1;
      this.theReadMessagesPageSize = data.size;
      this.theReadMessagesElements = data.totalElements;
    })
  }

  updateReadMessagesQuantity(pageSize: number) {
    this.theReadMessagesPageSize = pageSize;
    this.theReadMessagesPageNumber = 1;
    this.listOfReadMessages();
  }
  
  listOfUnreadMessages() {
    this.messageService.getAllUnreadMessagesByUser(this.theUnreadMessagesPageNumber - 1, this.theUnreadMessagesPageSize).subscribe(data => {
      this.messageUnreadList = data.content;
      this.theUnreadMessagesPageNumber = data.number + 1;
      this.theUnreadMessagesPageSize = data.size;
      this.theUnreadMessagesElements = data.totalElements;
    })
  }

  updateUnreadMessagesQuantity(pageSize: number) {
    this.theUnreadMessagesPageSize = pageSize;
    this.theUnreadMessagesPageNumber = 1;
    this.listOfUnreadMessages();
  }

  listOfSendMessages() {
    this.messageService.getAllSendMessagesByUser(this.theSendMessagesPageNumber - 1, this.theSendMessagesPageSize).subscribe(data => {
      this.messageSendList = data.content;
      this.theSendMessagesPageNumber = data.number + 1;
      this.theSendMessagesPageSize = data.size;
      this.theSendMessagesElements = data.totalElements;
    })
  }

  updateSendMessagesQuantity(pageSize: number) {
    this.theSendMessagesPageSize = pageSize;
    this.theSendMessagesPageNumber = 1;
    this.listOfSendMessages();
  }

  changeStatus(messageId: number) {
    this.messageService.changeMessageStatus(messageId);
  }

  transformDate(date) {
    return this.datePipe.transform(date, 'yyyy-MM-dd');
  }

}
