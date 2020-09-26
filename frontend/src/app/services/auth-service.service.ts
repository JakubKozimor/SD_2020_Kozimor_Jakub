import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Credentials } from '../common/credentials';
import { LoginResponse } from '../common/login-response';
import { TokenDecoded } from '../common/token-decoded';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private url = 'http://localhost:8080';
  private isLoggedInSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.loggedIn);

  constructor(private http: HttpClient) { }

  login(credentials: Credentials): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      this.http.post(this.url + '/auth/login', credentials).subscribe(
        (data: LoginResponse) => {
          this.saveToken("Bearer " + data.accessToken);
          const tokenDecoded: TokenDecoded = JSON.parse(atob(data.accessToken.split('.')[1]));
          localStorage.setItem('user_id', (+tokenDecoded.sub).toString());
          this.isLoggedInSubject.next(this.loggedIn);
          window.location.reload();
          // this.router.navigate(['']);

        },
        (error) => {
          console.log("Login error")
        }
      );
    });
  }

  saveToken(token: string): void {
    localStorage.setItem('access_token', token);
  }

  get loggedIn(): boolean {
    return this.getToken() !== null;
  }

  getToken(): string {
    return localStorage.getItem('access_token');
  }
}
