import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Teacher } from '../common/teacher';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
  private BASE_URL = 'http://localhost:8080/teachers';
  constructor(private httpClient: HttpClient) { }



  public getAllTeachersByUser(
    pageIndex: number,
    pageSize: number,
  ): Observable<GetResponseTeacher> {

    let params = this.getPageParams(pageIndex, pageSize);
    const userId = this.getAcctualUserId();

    return this.httpClient.get<GetResponseTeacher>(`${this.BASE_URL}/${userId}/all`, { params });
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
export interface GetResponseTeacher {
  content: Teacher[];
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}