import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HomeworkAnswer } from "../common/homework-answer";
import { HomeworkAnswerDetails } from "../common/homework-answer-details";
import { HomeworkAnswerDto } from "../common/homework-answer-dto";

const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type": "application/json",
    charset: "UTF-8",
  }),
};

@Injectable({
  providedIn: "root",
})
export class HomeworkAnswerService {
  private BASE_URL = "http://192.168.99.100:8080/homework-answer";

  constructor(private httpClient: HttpClient) {}

  getHomeworkDetails(homeworkId: number): Observable<HomeworkAnswerDetails> {
    let userId = this.getAcctualUserId();
    return this.httpClient.get<HomeworkAnswerDetails>(
      `${this.BASE_URL}/homework-answer-details/${homeworkId}?userId=${userId}`
    );
  }

  getHomeworkAnswerDetails(
    answerId: number
  ): Observable<HomeworkAnswerDetails> {
    return this.httpClient.get<HomeworkAnswerDetails>(
      `${this.BASE_URL}/homework-answer-details?answerId=${answerId}`
    );
  }

  addGrade(homeworkAnswerId: number, grade: string, comment: string) {
    this.httpClient
      .patch(
        `${this.BASE_URL}/grade/${homeworkAnswerId}?grade=${grade}&comment=${comment}`,
        httpOptions
      )
      .subscribe();
  }

  addAnswer(homeworkAnswer: HomeworkAnswer): Promise<any> {
    const userId = this.getAcctualUserId();
    homeworkAnswer.studentId = Number(userId);

    console.log(homeworkAnswer);
    return new Promise<any>((resolve, reject) => {
      this.httpClient.post(this.BASE_URL + `/add`, homeworkAnswer).subscribe(
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
    const userId = this.getAcctualUserId();
    homeworkAnswer.studentId = Number(userId);

    console.log(homeworkAnswer);
    return new Promise<any>((resolve, reject) => {
      this.httpClient.put(this.BASE_URL + `/update`, homeworkAnswer).subscribe(
        (data) => {
          resolve("Dodano");
        },
        (error) => {
          reject("Dodawanie nie powiodło się");
        }
      );
    });
  }

  getAllHomeworkAnswers(
    pageIndex: number,
    pageSize: number,
    homeworkId: number
  ): Observable<GetResponseHomreworkAnswer> {
    let params = this.getPageParams(pageIndex, pageSize);
    params = params.append("homeworkId", `${homeworkId}`);
    return this.httpClient.get<GetResponseHomreworkAnswer>(
      `${this.BASE_URL}/all-no-grade`,
      { params }
    );
  }

  getAllHomeworkAnswersWithGrade(
    pageIndex: number,
    pageSize: number,
    homeworkId: number
  ): Observable<GetResponseHomreworkAnswer> {
    let params = this.getPageParams(pageIndex, pageSize);
    params = params.append("homeworkId", `${homeworkId}`);
    return this.httpClient.get<GetResponseHomreworkAnswer>(
      `${this.BASE_URL}/all-grade`,
      { params }
    );
  }

  getPageParams(pageIndex: number, pageSize: number): HttpParams {
    return new HttpParams()
      .append("page", `${pageIndex}`)
      .append("size", `${pageSize}`);
  }

  getAcctualUserId() {
    return localStorage.getItem("user_id");
  }
}
export interface GetResponseHomreworkAnswer {
  content: HomeworkAnswerDto[];
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}
