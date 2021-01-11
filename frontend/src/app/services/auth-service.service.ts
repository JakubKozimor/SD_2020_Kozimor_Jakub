import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { Credentials } from "../common/credentials";
import { LoginResponse } from "../common/login-response";
import { TokenDecoded } from "../common/token-decoded";
import { User } from "../common/user";

@Injectable({
  providedIn: "root",
})
export class AuthServiceService {
  private url = "http://localhost:8080/auth";
  private isLoggedInSubject: BehaviorSubject<boolean> = new BehaviorSubject<
    boolean
  >(this.loggedIn);

  constructor(private http: HttpClient) {}

  login(credentials: Credentials): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      this.http.post(this.url + "/login", credentials).subscribe(
        (data: LoginResponse) => {
          this.saveToken("Bearer " + data.accessToken);
          const tokenDecoded: TokenDecoded = JSON.parse(
            atob(data.accessToken.split(".")[1])
          );
          localStorage.setItem("user_role", data.role);
          localStorage.setItem("user_id", (+tokenDecoded.sub).toString());
          this.isLoggedInSubject.next(this.loggedIn);
          window.location.reload();
        },
        (error) => {
          reject(error)
        }
      );
    });
  }

  register(user: User): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      this.http.post(this.url + "/register", user).subscribe(
        (data) => {
          resolve("Zarejestrowano");
        },
        (error) => {
          if (error.status === 409) {
            reject("Użytkownik z takim adresem email istnieje");
          }
          reject("Nieudana rejestracja");
        }
      );
    });
  }

  saveToken(token: string): void {
    localStorage.setItem("access_token", token);
  }

  get loggedIn(): boolean {
    return this.getToken() !== null;
  }

  getToken(): string {
    return localStorage.getItem("access_token");
  }
}
