import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subject } from '../common/subject';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {
  private BASE_URL = 'http://localhost:8080/subject';
  constructor(private httpClient: HttpClient) { }

  public getAllSubjectsByUser(userId: number): Observable<Subject[]> {
    return this.httpClient.get<Subject[]>(`${this.BASE_URL}/getAll?id=${userId}`);
  }

  public getFiveFirstSubjectsByUser(userId: number): Observable<Subject[]> {
    return this.httpClient.get<Subject[]>(`${this.BASE_URL}/getFiveFirst?id=${userId}`);
  }
}
