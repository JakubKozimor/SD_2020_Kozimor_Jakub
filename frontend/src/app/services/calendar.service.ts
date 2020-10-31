import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CalendarEvent } from 'angular-calendar';
import { CalendarDto } from '../common/calendar-dto';
import {startOfDay,subDays} from 'date-fns';
import { Observable } from 'rxjs';
import { NewEvent } from '../common/new-event';

const colors: any = {
  red: {
    primary: '#ad2121'
  },
  blue: {
    primary: '#1e90ff'
  },
  yellow: {
    primary: '#e3bc08'
  },
};


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
}
