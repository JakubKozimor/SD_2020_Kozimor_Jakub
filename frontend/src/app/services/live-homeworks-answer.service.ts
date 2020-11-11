import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { LiveHomeworkAnswer } from "../common/live-homework-answer";

@Injectable({
  providedIn: "root",
})
export class LiveHomeworksAnswerService {
  private BASE_URL = "http://localhost:8080/live-homework-answer";
  constructor(private httpClient: HttpClient) {}

  getActualUserAnswerDetails(
    liveHomeworkId: number
  ): Observable<LiveHomeworkAnswer> {
    const userId = this.getAcctualUserId();
    return this.httpClient.get<LiveHomeworkAnswer>(
      `${this.BASE_URL}/live-homework-answer-details/${liveHomeworkId}?userId=${userId}`
    );
  }

  addAnswer(
    liveHomeworkAnswer: LiveHomeworkAnswer
  ): Promise<any> {
    const userId = this.getAcctualUserId();
    liveHomeworkAnswer.studentId = Number(userId);
    return new Promise<any>((resolve, reject) => {
      this.httpClient
        .post(this.BASE_URL + `/add`, liveHomeworkAnswer)
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
    return localStorage.getItem("user_id");
  }
}
