import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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

  constructor(
    private httpClient: HttpClient
    ) { }

  addMessage(message: Message) {
    const userId = this.getAcctualUserId();
    message.userFrom = Number(userId);
    this.httpClient.post(`${this.BASE_URL}/new-message`, message).subscribe();
  }

  getAllReadMessagesByUser(
    pageIndex: number,
    pageSize: number,
  ): Observable<GetResponseMessage> {

    let params = this.getPageParams(pageIndex, pageSize);
    const userId = Number(this.getAcctualUserId());

    return this.httpClient.get<GetResponseMessage>(`${this.BASE_URL}/${userId}/all-read`, { params });
  }

  getAllUnreadMessagesByUser(
    pageIndex: number,
    pageSize: number,
  ): Observable<GetResponseMessage> {

    let params = this.getPageParams(pageIndex, pageSize);
    const userId = Number(this.getAcctualUserId());

    return this.httpClient.get<GetResponseMessage>(`${this.BASE_URL}/${userId}/all-unread`, { params });
  }

  getAllSentMessagesByUser(
    pageIndex: number,
    pageSize: number,
  ): Observable<GetResponseMessage> {

    let params = this.getPageParams(pageIndex, pageSize);
    const userId = Number(this.getAcctualUserId());

    return this.httpClient.get<GetResponseMessage>(`${this.BASE_URL}/${userId}/all-sent`, { params });
  }

  getMessageDetails(messageId: number): Observable<MessageDetails> {
    return this.httpClient.get<MessageDetails>(`${this.BASE_URL}/message-details/${messageId}`);
  }

  changeMessageStatus(messageId: number) {
    this.httpClient.patch(`${this.BASE_URL}/update-status-message/${messageId}`, httpOptions).subscribe();
  }

  getPageParams(pageIndex: number, pageSize: number): HttpParams {
    return new HttpParams()
      .append('page', `${pageIndex}`)
      .append('size', `${pageSize}`);
  }

  getAcctualUserId() {
    return localStorage.getItem('user_id');
  }
}


export interface GetResponseMessage {
  content: Message[];
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}