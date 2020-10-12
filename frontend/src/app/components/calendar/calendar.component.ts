import { Component, Injectable, OnInit } from '@angular/core';
import { SubjectService } from 'src/app/services/subject.service';
import { Subject } from 'src/app/common/subject';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { Global } from 'src/app/global';


@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  pageIndex: number;
  pageSize: number;
  subjectsList: Subject[];

  week = "ALL";

  constructor(
    private subjectService: SubjectService,
    private router: Router,
    private global: Global) { }

  ngOnInit(): void {
    this.listOfSubjects();
  }

  listOfSubjects() {
    this.pageIndex = 0;
    this.pageSize = 100;
    this.subjectService.getAllSubjectsByUser(this.pageIndex, this.pageSize, this.week).subscribe(data => this.subjectsList = data.content)
  }

  changeWeek(week: string) {
    this.week = week;
    this.global.setWeek(week);
    this.changeRoute(week);
    this.ngOnInit();
  }

  changeRoute(week: string){
    if(this.router.url.includes("menu")){
      this.router.navigate(['menu/'+week]);
    }
  }

}
