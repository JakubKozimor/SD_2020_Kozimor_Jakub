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

  constructor(private messageService: MessageService) { }

  ngOnInit(): void {
    this.listOfReadMessages();
    this.listOfUnreadMessages();
  }

  listOfReadMessages(){
    this.messageService.getAllReadMEssagesByUser(3).subscribe(data => this.messageReadList = data.content)
  }

  listOfUnreadMessages(){
    this.messageService.getAllUnreadMEssagesByUser(3).subscribe(data => this.messageReadList = data.content)

  }

}
