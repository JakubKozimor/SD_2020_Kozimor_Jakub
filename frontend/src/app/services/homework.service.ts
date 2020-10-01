import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Homework } from '../common/homework';
import { HomeworkDetails } from '../common/homework-details';

@Injectable({
  providedIn: 'root'
})
export class HomeworkService {
  private BASE_URL = 'http://localhost:8080/homeworks';
  constructor(private httpClient: HttpClient) { }

  getAllActiveHomeworkByUser(
    pageIndex: number,
    pageSize: number,
  ): Observable<GetResponseHomework> {

    let params = this.getPageParams(pageIndex, pageSize);
    const userId = this.getAcctualUserId();

    return this.httpClient.get<GetResponseHomework>(`${this.BASE_URL}/${userId}/active`, { params });
  }

  getFiveFirstHomeworksByUser(): Observable<Homework[]> {
    const userId = this.getAcctualUserId();
    return this.httpClient.get<Homework[]>(`${this.BASE_URL}/${userId}/five-first`);
  }

  getPageParams(pageIndex: number, pageSize: number): HttpParams {
    return new HttpParams()
      .append('page', `${pageIndex}`)
      .append('size', `${pageSize}`);
  }

  getAcctualUserId() {
    return localStorage.getItem('user_id');
  }

  getHomeworkDetails(homeworkId: number): Observable<HomeworkDetails> {
    return this.httpClient.get<HomeworkDetails>(`${this.BASE_URL}/homework-details/${homeworkId}`);
  }

  createHomework(homework: Homework, subjectId: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      this.httpClient.post(this.BASE_URL + `/${subjectId}/add`, homework)
        .subscribe(
          (data) => {
            resolve("Dodano");
          },
          (error) => {
            // if (error.status === 409) {
            //   reject("Użytkownik z takim adresem email istnieje");
            // }
            reject("Dodawanie nie powiodło się");
          }
        );
    });

  }
}

export interface GetResponseHomework {
  content: Homework[];
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}