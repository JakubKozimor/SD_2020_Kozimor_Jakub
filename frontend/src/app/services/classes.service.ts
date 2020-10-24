import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Classes } from '../common/classes';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClassesService {

  private BASE_URL = 'http://localhost:8080/lessons';

  constructor(
    private httpClient: HttpClient
  ) { }

  createClasses(classes: Classes): Observable<number> {
    let userId = Number(this.getAcctualUserId());
    return this.httpClient.post<number>(this.BASE_URL + `/new?userId=${userId}`, classes).pipe();

  }

  getClassesDetails(classesId: number): Observable<Classes> {
    return this.httpClient.get<Classes>(`${this.BASE_URL}/lesson-details/${classesId}`);
  }

  getLiveClasses(): Observable<Classes[]>{
    let userId = Number(this.getAcctualUserId());
    return this.httpClient.get<Classes[]>(`${this.BASE_URL}/${userId}`);
  }

  getAcctualUserId() {
    return localStorage.getItem('user_id');
  }
}
