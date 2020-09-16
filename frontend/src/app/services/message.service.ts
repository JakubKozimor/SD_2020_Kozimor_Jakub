import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Message } from '../common/message';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'charset': 'UTF-8'
  })
};

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private BASE_URL = 'http://localhost:8080/messages/newMessage';

  constructor(private httpClient: HttpClient) { }

  addMessage(message: Message){
    console.log(message);
    this.httpClient.post(this.BASE_URL,message).subscribe();
  }
}
