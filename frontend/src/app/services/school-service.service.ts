import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { School } from "../common/school";

@Injectable({
  providedIn: "root",
})
export class SchoolServiceService {
  private BASE_URL = "http://localhost:8080/school";
  constructor(private httpClient: HttpClient) {}

  public getAllSchools(
    pageIndex: number,
    pageSize: number
  ): Observable<GetResponseSchool> {
    let params = this.getPageParams(pageIndex, pageSize);
    return this.httpClient.get<GetResponseSchool>(`${this.BASE_URL}/all`, {
      params,
    });
  }

  addSchool(school: School): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      this.httpClient.post(this.BASE_URL + `/new`, school).subscribe(
        (data) => {
          resolve("Dodano");
        },
        (error) => {
          reject("Dodawanie nie powiodło się");
        }
      );
    });
  }

  updateSchool(school: School, schoolId: number): Promise<any> {
    school.schoolId = schoolId;
    console.log(schoolId);
    return new Promise<any>((resolve, reject) => {
      this.httpClient.put(this.BASE_URL + `/update`, school).subscribe(
        (data) => {
          resolve("Zaktualizowano");
        },
        (error) => {
          reject("Aktualizacja nie powiodło się");
        }
      );
    });
  }

  getSchoolDetails(schoolId: number): Observable<School> {
    return this.httpClient.get<School>(`${this.BASE_URL}/details/${schoolId}`);
  }

  getPageParams(pageIndex: number, pageSize: number): HttpParams {
    return new HttpParams()
      .append("page", `${pageIndex}`)
      .append("size", `${pageSize}`);
  }
}
export interface GetResponseSchool {
  content: School[];
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}
