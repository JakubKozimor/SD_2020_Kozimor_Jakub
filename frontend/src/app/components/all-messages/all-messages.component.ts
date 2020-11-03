import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Message } from 'src/app/common/message';
import { UserDto } from 'src/app/common/user-dto';
import { MessageService } from 'src/app/services/message.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-all-messages',
  templateUrl: './all-messages.component.html',
  styleUrls: ['./all-messages.component.css']
})
export class AllMessagesComponent implements OnInit {
  messageReadList: Message[];
  messageUnreadList: Message[];
  messageSentList: Message[];

  theSentMessagesPageNumber: number = 1;
  theSentMessagesPageSize: number = 5;
  theSentMessagesElements: number = 0;

  theUnreadMessagesPageNumber: number = 1;
  theUnreadMessagesPageSize: number = 5;
  theUnreadMessagesElements: number = 0;

  theReadMessagesPageNumber: number = 1;
  theReadMessagesPageSize: number = 5;
  theReadMessagesElements: number = 0;

  studentsList: UserDto[];

  thePageNumber: number = 1;
  thePageSize: number = 5;
  theElements: number = 0;

  searchValue = "";

  constructor(
    private messageService: MessageService,
    private userService: UserService,
    private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.listOfReadMessages();
    this.listOfUnreadMessages();
    this.listOfSentMessages();
    this.listOfStudents();
  }

  listOfStudents() {
    this.userService.searchStudents(this.searchValue, this.thePageNumber - 1, this.thePageSize).subscribe(data => {
      this.studentsList = data.content;
      this.thePageNumber = data.number + 1;
      this.thePageSize = data.size;
      this.theElements = data.totalElements;
    })
  }

  search(search: string) {
    this.searchValue = search;
    this.listOfStudents();
  }

  updateQuantity(pageSize: number) {
    this.thePageSize = pageSize;
    this.thePageNumber = 1;
    this.listOfStudents();
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

  listOfSentMessages() {
    this.messageService.getAllSentMessagesByUser(this.theSentMessagesPageNumber - 1, this.theSentMessagesPageSize).subscribe(data => {
      this.messageSentList = data.content;
      this.theSentMessagesPageNumber = data.number + 1;
      this.theSentMessagesPageSize = data.size;
      this.theSentMessagesElements = data.totalElements;
    })
  }

  updateSentMessagesQuantity(pageSize: number) {
    this.theSentMessagesPageSize = pageSize;
    this.theSentMessagesPageNumber = 1;
    this.listOfSentMessages();
  }

  changeStatus(messageId: number) {
    this.messageService.changeMessageStatus(messageId);
  }

  transformDate(date) {
    return this.datePipe.transform(date, 'yyyy-MM-dd');
  }

}
