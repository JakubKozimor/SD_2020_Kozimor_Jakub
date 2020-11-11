import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { LiveHomework } from "../common/live-homework";
import { LiveHomeworkAnswer } from "../common/live-homework-answer";
import { LiveHomeworkFile } from "../common/live-homework-file";
import { LiveHomeworksDetails } from "../common/live-homeworks-details";

@Injectable({
  providedIn: "root",
})
export class LiveHomeworkService {
  private BASE_URL = "http://localhost:8080/live-homeworks";
  constructor(private httpClient: HttpClient) {}

  getHomeworkDetails(classesid: number): Observable<LiveHomeworksDetails> {
    return this.httpClient.get<LiveHomeworksDetails>(
      `${this.BASE_URL}/live-homework-details/${classesid}`
    );
  }

  updateLiveHomework(
    liveHomework: LiveHomework,
    liveHomeworkId: number
  ): Promise<any> {
    liveHomework.liveHomeworkId = liveHomeworkId;
    return new Promise<any>((resolve, reject) => {
      this.httpClient.put(this.BASE_URL + `/update`, liveHomework).subscribe(
        (data) => {
          resolve("Dodano");
        },
        (error) => {
          reject("Dodawanie nie powiodło się");
        }
      );
    });
  }

  createLiveHomework(
    liveHomework: LiveHomework,
    classesId: number
  ): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      this.httpClient
        .post(this.BASE_URL + `/add/${classesId}`, liveHomework)
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

  getAllLiveHomeworks(classesId: number): Observable<LiveHomework[]> {
    return this.httpClient.get<LiveHomework[]>(
      `${this.BASE_URL}/all?lessonId=${classesId}`
    );
  }
}
