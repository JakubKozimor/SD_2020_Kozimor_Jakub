import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
  })
export class Global {

    week = 'ALL';

    setWeek(week: string){
        this.week = week;
    }

    getWeek(): string{
        return this.week;
    }
}
