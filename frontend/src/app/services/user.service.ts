import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../common/user";
import { UserDto } from "../common/user-dto";
import { UsersList } from "../common/users-list";

@Injectable({
  providedIn: "root",
})
export class UserService {
  private BASE_URL = "http://localhost:8080/users";

  constructor(private httpClient: HttpClient) {}

  public searchStudents(
    search: string,
    pageIndex: number,
    pageSize: number
  ): Observable<GetResponseUser> {
    const userId = Number(this.getAcctualUserId());
    let params = this.getPageParams(pageIndex, pageSize);
    params = params.append("search", `${search}`);
    params = params.append("userId", `${userId}`);
    return this.httpClient.get<GetResponseUser>(`${this.BASE_URL}/search`, {
      params,
    });
  }

  public getAllStudents(
    pageIndex: number,
    pageSize: number,
    subjectId: number
  ): Observable<GetResponseUser> {
    let params = this.getPageParams(pageIndex, pageSize);
    params = params.append("subjectId", `${subjectId}`);
    return this.httpClient.get<GetResponseUser>(`${this.BASE_URL}/all`, {
      params,
    });
  }

  deleteFromSubject(userId: number, subjectId: number) {
    this.httpClient
      .delete(`${this.BASE_URL}/${userId}?subjectId=${subjectId}`)
      .subscribe();
  }

  getPageParams(pageIndex: number, pageSize: number): HttpParams {
    return new HttpParams()
      .append("page", `${pageIndex}`)
      .append("size", `${pageSize}`);
  }

  addStudents(users, subjectId) {
    let studentsList = new UsersList();
    studentsList.students = users;
    this.httpClient
      .post<number>(
        this.BASE_URL + `/users?subjectId=${subjectId}`,
        studentsList
      )
      .subscribe();
  }

  getAcctualUserId() {
    return localStorage.getItem("user_id");
  }

  getActualUserRole() {
    return localStorage.getItem("user_role");
  }

  // let role = this.getActualUserRole();
  // if(role == 'ROLE_TEACHER'){

  // }
  // if(role == 'ROLE_STUDENT'){

  // }
}

export interface GetResponseUser {
  content: UserDto[];
  size: number;
  totalElements: number;
  totalPages: number;
  number: number;
}
