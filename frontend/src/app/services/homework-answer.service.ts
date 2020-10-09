import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HomeworkAnswer } from '../common/homework-answer';

@Injectable({
  providedIn: 'root'
})
export class HomeworkAnswerService {

  private BASE_URL = 'http://localhost:8080/homework-answer';

  constructor(
    private httpClient: HttpClient
  ) { }

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

  getAcctualUserId() {
    return localStorage.getItem('user_id');
  }
}
