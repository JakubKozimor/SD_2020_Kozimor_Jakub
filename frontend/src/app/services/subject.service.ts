import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subject } from '../common/subject';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {
  private BASE_URL = 'http://localhost:8080/subjects';

  constructor(private httpClient: HttpClient) { }
  
  public getAllSubjectsByUser(
    pageIndex: number,
    pageSize: number,
    week: string
  ): Observable<GetResponseSubject> {

    let params = this.getPageParams(pageIndex, pageSize);
    params = params.append('week', `${week}`);
    const userId = Number(this.getAcctualUserId());
    return this.httpClient.get<GetResponseSubject>(`${this.BASE_URL}/${userId}/all`, { params });
  }

  public getAllSubjectsForTeacher(
    pageIndex: number,
    pageSize: number,
    week: string
  ): Observable<GetResponseSubject> {
    let params = this.getPageParams(pageIndex, pageSize);
    params = params.append('week', `${week}`);
    const userId = Number(this.getAcctualUserId());
    return this.httpClient.get<GetResponseSubject>(`${this.BASE_URL}/${userId}/all-teacher`, { params });
  }

  addMessage(subject: Subject): Observable<number>{
    let userId = Number(this.getAcctualUserId());
    return this.httpClient.post<number>(this.BASE_URL + `/new?teacherId=${userId}`, subject).pipe();
  }

  public getFiveFirstSubjectsByUser(week:string): Observable<Subject[]> {
    const userId = this.getAcctualUserId();
    let role = this.getActualUserRole();
    if(role == 'ROLE_TEACHER'){
      return this.httpClient.get<Subject[]>(`${this.BASE_URL}/${userId}/five-first-teacher?week=${week}`);
    }
    if(role == 'ROLE_STUDENT'){
      return this.httpClient.get<Subject[]>(`${this.BASE_URL}/${userId}/five-first?week=${week}`);
    }
  }

  getPageParams(pageIndex: number, pageSize: number): HttpParams {
    return new HttpParams()
      .append('page', `${pageIndex}`)
      .append('size', `${pageSize}`);
  }

  getAcctualUserId() {
    return localStorage.getItem('user_id');
  }

  getActualUserRole(){
    return localStorage.getItem("user_role");
  }
}

export interface GetResponseSubject {
  content: Subject[];
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}
