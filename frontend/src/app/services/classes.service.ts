import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Classes } from "../common/classes";
import { Observable } from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'charset': 'UTF-8'
  })
};

@Injectable({
  providedIn: "root",
})
export class ClassesService {
  private BASE_URL = "http://192.168.99.100:8080/lessons";

  constructor(private httpClient: HttpClient) {}

  createClasses(classes: Classes): Observable<number> {
    let userId = Number(this.getAcctualUserId());
    return this.httpClient
      .post<number>(this.BASE_URL + `/new?userId=${userId}`, classes)
      .pipe();
  }

  updateClasses(classes: Classes): Observable<number> {
    return this.httpClient
      .put<number>(this.BASE_URL + `/update`, classes)
      .pipe();
  }

  getClassesDetails(classesId: number): Observable<Classes> {
    return this.httpClient.get<Classes>(
      `${this.BASE_URL}/lesson-details/${classesId}`
    );
  }

  getLiveClasses(): Observable<Classes[]> {
    let userId = Number(this.getAcctualUserId());
    return this.httpClient.get<Classes[]>(`${this.BASE_URL}/${userId}`);
  }

  getLiveClassesForTeacher(): Observable<Classes[]> {
    let userId = Number(this.getAcctualUserId());
    return this.httpClient.get<Classes[]>(`${this.BASE_URL}/teacher/${userId}`);
  }

  finishLive(classesId: number){
    this.httpClient.patch(`${this.BASE_URL}/finish/${classesId}`, httpOptions).subscribe();
  }

  getAcctualUserId() {
    return localStorage.getItem("user_id");
  }
}
