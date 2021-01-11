import { Component, Injectable, OnInit } from "@angular/core";
import { Subject } from "src/app/common/subject";
import { SubjectService } from "src/app/services/subject.service";
import { HomeworkService } from "src/app/services/homework.service";
import { Homework } from "src/app/common/homework";
import { ActivatedRoute, Router } from "@angular/router";
import { Global } from "src/app/global";
import { CalendarService } from "src/app/services/calendar.service";
import { HomeworkDetails } from "src/app/common/homework-details";
import { HomeworkForFirstView } from "src/app/common/homework-for-first-view";

@Component({
  selector: "app-content",
  templateUrl: "./content.component.html",
  styleUrls: ["./content.component.css"],
})
export class ContentComponent implements OnInit {
  pageIndex: number;
  pageSize: number;

  homeworkList: HomeworkForFirstView[];

  hour: number;
  minute: number;
  subjectTime: number;

  isTeacher: boolean;

  subjectsList: Subject[];

  constructor(
    private subjectService: SubjectService,
    private homeworkService: HomeworkService,
    private route: ActivatedRoute,
    private calendarService: CalendarService,
    private global: Global
  ) {}

  ngOnInit(): void {
    this.isTeacher = this.global.isTeacher();
    this.listOfFiveSubjects();
    this.listOfHomework();
    this.route.params.subscribe((routeParams) => {
      this.listOfFiveSubjects();
    });
  }

  listOfFiveSubjects() {
    this.calendarService.getActualWeek().subscribe((data) => {
      this.subjectService
        .getFiveFirstSubjectsByUser(data.week)
        .subscribe((data) => (this.subjectsList = data));
    });
  }

  listOfHomework() {
    this.pageIndex = 0;
    this.pageSize = 100;
    this.homeworkService
      .getFiveFirstHomeworksByUser()
      .subscribe((data) => (this.homeworkList = data));
  }

  countTime(startTime: string, longOfTime: string) {
    if (isNaN(Number(startTime.substr(0, 2)))) {
      this.hour = Number(startTime.substr(0, 1));
      this.minute = Number(startTime.substr(2, 3));
    } else {
      this.hour = Number(startTime.substr(0, 2));
      this.minute = Number(startTime.substr(3, 4));
    }
    this.subjectTime = Number(longOfTime);
    this.minute += this.subjectTime;

    while (this.minute >= 60) {
      this.minute -= 60;
      this.hour += 1;
    }

    if (this.minute < 10) {
      return String(this.hour) + ":0" + this.minute;
    } else {
      return String(this.hour) + ":" + this.minute;
    }
  }

  getDay(day: string) {
    if (day == "MONDAY") {
      return "Poniedziałek";
    }
    if (day == "TUESDAY") {
      return "Wtorek";
    }
    if (day == "WEDNESDAY") {
      return "Środa";
    }
    if (day == "THURSDAY") {
      return "Czwartek";
    }
    if (day == "FRIDAY") {
      return "Piątek";
    }
  }

  getActualUserRole() {
    return localStorage.getItem("user_role");
  }
}
