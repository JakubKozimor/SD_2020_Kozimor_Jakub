import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Teacher } from '../common/teacher';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
  private BASE_URL = 'http://localhost:8080/teachers';
  constructor(private httpClient: HttpClient) { }

  public getAllTeachersByUser(userId: number): Observable<GetResponseTeacher> {
    return this.httpClient.get<GetResponseTeacher>(`${this.BASE_URL}/all?id=${userId}&page=0&size=10`);
  }
}
export interface GetResponseTeacher {
  content: Teacher[];
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}