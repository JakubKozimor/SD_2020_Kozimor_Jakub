import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Homework } from '../common/homework';

@Injectable({
  providedIn: 'root'
})
export class HomeworkService {

  private BASE_URL = 'http://localhost:8080/homeworks';
  constructor(private httpClient: HttpClient) { }

  public getAllSubjectsByUser(userId: number): Observable<GetResponseHomework> {
    return this.httpClient.get<GetResponseHomework>(`${this.BASE_URL}/all?id=${userId}&page=0&size=10`);
  }
}

export interface GetResponseHomework {
  content: Homework[];
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}