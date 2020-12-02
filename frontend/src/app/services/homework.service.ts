import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { Homework } from "../common/homework";
import { HomeworkDetails } from "../common/homework-details";
import { RatedHomework } from "../common/rated-homework";

@Injectable({
  providedIn: "root",
})
export class HomeworkService {
  private BASE_URL = "http://192.168.99.100:8080/homeworks";
  constructor(private httpClient: HttpClient) {}

  getAllActiveHomeworksForTeacher(
    pageIndex: number,
    pageSize: number
  ): Observable<GetResponseHomework> {
    let params = this.getPageParams(pageIndex, pageSize);
    const userId = this.getAcctualUserId();
    return this.httpClient.get<GetResponseHomework>(
      `${this.BASE_URL}/active/${userId}`,
      { params }
    );
  }

  getAllNotRatedHomeworks(
    pageIndex: number,
    pageSize: number
  ): Observable<GetResponseHomework> {
    let params = this.getPageParams(pageIndex, pageSize);
    const userId = this.getAcctualUserId();
    return this.httpClient.get<GetResponseHomework>(
      `${this.BASE_URL}/not-rated/${userId}`,
      { params }
    );
  }

  getAllRatedHomeworks(
    pageIndex: number,
    pageSize: number
  ): Observable<GetResponseHomework> {
    let params = this.getPageParams(pageIndex, pageSize);
    const userId = this.getAcctualUserId();
    return this.httpClient.get<GetResponseHomework>(
      `${this.BASE_URL}/rated/${userId}`,
      { params }
    );
  }

  getAllActiveHomeworkByUser(
    pageIndex: number,
    pageSize: number,
    subjectId: number
  ): Observable<GetResponseHomework> {
    let params = this.getPageParams(pageIndex, pageSize);
    params = params.append("subjectId", `${subjectId}`);
    const userId = this.getAcctualUserId();

    return this.httpClient.get<GetResponseHomework>(
      `${this.BASE_URL}/${userId}/active`,
      { params }
    );
  }

  getAllDoneHomeworkByUser(
    pageIndex: number,
    pageSize: number,
    subjectId: number
  ): Observable<GetResponseHomework> {
    let params = this.getPageParams(pageIndex, pageSize);
    params = params.append("subjectId", `${subjectId}`);
    const userId = this.getAcctualUserId();

    return this.httpClient.get<GetResponseHomework>(
      `${this.BASE_URL}/${userId}/done`,
      { params }
    );
  }

  getAllLateHomeworkByUser(
    pageIndex: number,
    pageSize: number,
    subjectId: number
  ): Observable<GetResponseHomework> {
    let params = this.getPageParams(pageIndex, pageSize);
    params = params.append("subjectId", `${subjectId}`);
    const userId = this.getAcctualUserId();

    return this.httpClient.get<GetResponseHomework>(
      `${this.BASE_URL}/${userId}/late`,
      { params }
    );
  }

  getAllRatedHomeworkByUser(
    pageIndex: number,
    pageSize: number,
    subjectId: number
  ): Observable<GetResponseRatedHomework> {
    let params = this.getPageParams(pageIndex, pageSize);
    params = params.append("subjectId", `${subjectId}`);
    const userId = this.getAcctualUserId();

    return this.httpClient.get<GetResponseRatedHomework>(
      `${this.BASE_URL}/${userId}/rated`,
      { params }
    );
  }

  getFiveFirstHomeworksByUser(): Observable<Homework[]> {
    const userId = this.getAcctualUserId();
    let role = this.getActualUserRole();
    if (role == "ROLE_TEACHER") {
      return this.httpClient.get<Homework[]>(
        `${this.BASE_URL}/${userId}/five-first-teacher`
      );
    }
    if (role == "ROLE_STUDENT") {
      return this.httpClient.get<Homework[]>(
        `${this.BASE_URL}/${userId}/five-first`
      );
    }
  }

  getPageParams(pageIndex: number, pageSize: number): HttpParams {
    return new HttpParams()
      .append("page", `${pageIndex}`)
      .append("size", `${pageSize}`);
  }

  getAcctualUserId() {
    return localStorage.getItem("user_id");
  }

  getActualUserRole() {
    return localStorage.getItem("user_role");
  }

  getHomeworkDetails(homeworkId: number): Observable<HomeworkDetails> {
    return this.httpClient.get<HomeworkDetails>(
      `${this.BASE_URL}/homework-details/${homeworkId}`
    );
  }

  createHomework(homework: Homework, subjectId: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      this.httpClient
        .post(this.BASE_URL + `/${subjectId}/add`, homework)
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

  updateHomework(homework: Homework, homeworkId: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      this.httpClient
        .put(this.BASE_URL + `/${homeworkId}`, homework)
        .subscribe(
          (data) => {
            resolve("Zaktualizowano");
          },
          (error) => {
            reject("Aktualizacja nie powiodło się");
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

export interface GetResponseRatedHomework {
  content: RatedHomework[];
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}
