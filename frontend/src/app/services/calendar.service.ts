import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CalendarEvent } from 'angular-calendar';
import { Observable } from 'rxjs';
import { NewEvent } from '../common/new-event';
import { ActualWeekDto } from '../common/actual-week-dto';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  private BASE_URL = 'http://localhost:8080/calendar';
  events: CalendarEvent[] = [];
  constructor(
    private httpClient: HttpClient
  ) { }

  getAllEvents(
    schoolId: number
  ): Observable<NewEvent[]> {
    return this.httpClient.get<NewEvent[]>(`${this.BASE_URL}/all?schoolId=${schoolId}`).pipe();
  }

  getActualWeek(): Observable<ActualWeekDto> {
    let userId = Number(this.getAcctualUserId());
    return this.httpClient.get<ActualWeekDto>(`${this.BASE_URL}/actual-week?userId=${userId}`).pipe();
  }

  addEvent(newEvent: NewEvent, schoolId: number){
    return new Promise<any>((resolve, reject) => {
      this.httpClient.post(this.BASE_URL + `/${schoolId}`, newEvent)
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

  removeEvent(newEvent: NewEvent, schoolId: number){
    return new Promise<any>((resolve, reject) => {
      this.httpClient.post(this.BASE_URL + `/delete/${schoolId}`, newEvent)
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
