import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from '../common/message';
import { MessageDetails } from '../common/message-details';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'charset': 'UTF-8'
  })
};

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private BASE_URL = 'http://localhost:8080/messages';

  constructor(private httpClient: HttpClient) { }

  addMessage(message: Message) {
    this.httpClient.post(`${this.BASE_URL}/new-message`, message).subscribe();
  }

  getAllReadMessagesByUser(userId: number): Observable<GetResponseMessage> {
    return this.httpClient.get<GetResponseMessage>(`${this.BASE_URL}/all-read?id=${userId}&page=0&size=10`);
  }

  getAllUnreadMessagesByUser(userId: number): Observable<GetResponseMessage> {
    return this.httpClient.get<GetResponseMessage>(`${this.BASE_URL}/all-unread?id=${userId}&page=0&size=10`);
  }

  getMessageDetails(messageId: number): Observable<MessageDetails> {
    return this.httpClient.get<MessageDetails>(`${this.BASE_URL}//message-details/${messageId}`);
  }

  changeMessageStatus(messageId: number){
    this.httpClient.patch(`${this.BASE_URL}/update-status-message/${messageId}`,httpOptions).subscribe();
  }
}


export interface GetResponseMessage {
  content: Message[];
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}