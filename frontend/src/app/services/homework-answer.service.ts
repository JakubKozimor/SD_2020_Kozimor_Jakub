import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HomeworkAnswer } from '../common/homework-answer';
import { HomeworkAnswerDetails } from '../common/homework-answer-details';

@Injectable({
  providedIn: 'root'
})
export class HomeworkAnswerService {

  private BASE_URL = 'http://localhost:8080/homework-answer';

  constructor(
    private httpClient: HttpClient
  ) { }

  getHomeworkDetails(homeworkId: number): Observable<HomeworkAnswerDetails> {
    let userId = this.getAcctualUserId();
    return this.httpClient.get<HomeworkAnswerDetails>(`${this.BASE_URL}/homework-answer-details/${homeworkId}?userId=${userId}`);
  }

  addAnswer(homeworkAnswer: HomeworkAnswer): Promise<any> {
    const userId = this.getAcctualUserId()
    homeworkAnswer.studentId = Number(userId);
    
    console.log(homeworkAnswer)
    return new Promise<any>((resolve, reject) => {
      this.httpClient.post(this.BASE_URL + `/add`, homeworkAnswer)
        .subscribe(
          (data) => {
            resolve("Dodano");
          },
          (error) => {
            reject("Dodawanie nie powiodło się");
          }
        );
    });
  }

  updateAnswer(homeworkAnswer: HomeworkAnswer): Promise<any> {
    const userId = this.getAcctualUserId()
    homeworkAnswer.studentId = Number(userId);
    
    console.log(homeworkAnswer)
    return new Promise<any>((resolve, reject) => {
      this.httpClient.put(this.BASE_URL + `/update`, homeworkAnswer)
        .subscribe(
          (data) => {
            resolve("Dodano");
          },
          (error) => {
            reject("Dodawanie nie powiodło się");
          }
        );
    });
  }

  getAcctualUserId() {
    return localStorage.getItem('user_id');
  }
}
