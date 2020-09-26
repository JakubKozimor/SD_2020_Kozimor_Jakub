import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Homework } from '../common/homework';

@Injectable({
  providedIn: 'root'
})
export class HomeworkService {
  private BASE_URL = 'http://localhost:8080/homeworks';
  constructor(private httpClient: HttpClient) { }

  public getAllSubjectsByUser(
    pageIndex: number,
    pageSize: number,
  ): Observable<GetResponseHomework> {

    let params = this.getPageParams(pageIndex, pageSize);
    const userId = this.getAcctualUserId();

    return this.httpClient.get<GetResponseHomework>(`${this.BASE_URL}/${userId}/all`, { params });
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

export interface GetResponseHomework {
  content: Homework[];
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}