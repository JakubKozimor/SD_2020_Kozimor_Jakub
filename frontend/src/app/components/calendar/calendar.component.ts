import { Component, OnInit, ViewChild, ElementRef } from "@angular/core";
import { startOfDay, subDays } from "date-fns";
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
  DAYS_OF_WEEK,
} from "angular-calendar";
import { SubjectService } from "src/app/services/subject.service";
import { Subject } from "src/app/common/subject";
import { Global } from "src/app/global";
import { CalendarService } from "src/app/services/calendar.service";
import { registerLocaleData } from "@angular/common";
import localePl from "@angular/common/locales/pl";

registerLocaleData(localePl);

const colors: any = {
  red: {
    primary: "#e62e47",
    secondary: "#FAE3E3",
  },
  blue: {
    primary: "#3bb598",
    secondary: "#D1E8FF",
  },
  yellow: {
    primary: "#f7af28",
    secondary: "#FDF1BA",
  },
};

@Component({
  selector: "app-calendar",
  templateUrl: "./calendar.component.html",
  styleUrls: ["./calendar.component.css"],
})
export class CalendarComponent implements OnInit {
  pageIndex: number;
  pageSize: number;
  subjectsList: Subject[];
  subjectsListMonday: Subject[] = new Array();
  subjectsListTuesday: Subject[] = new Array();
  subjectsListWednesday: Subject[] = new Array();
  subjectsListThursday: Subject[] = new Array();
  subjectsListFriday: Subject[] = new Array();

  week: string;

  @ViewChild("myDiv") myDiv: ElementRef<HTMLElement>;
  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  modalData: {
    action: string;
    event: CalendarEvent;
  };

  actions: CalendarEventAction[] = [
    {
      label: '<i class="fas fa-fw fa-pencil-alt"></i>',
      a11yLabel: "Edit",
      onClick: ({ event }: { event: CalendarEvent }): void => {},
    },
    {
      label: '<i class="fas fa-fw fa-trash-alt"></i>',
      a11yLabel: "Delete",
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
      },
    },
  ];

  locale = "pl";
  weekendDays: number[] = [DAYS_OF_WEEK.SATURDAY, DAYS_OF_WEEK.SUNDAY];
  weekStartsOn: number = DAYS_OF_WEEK.MONDAY;

  events: CalendarEvent[] = [];
  newevents: CalendarEvent[] = [];

  activeDayIsOpen: boolean = true;
  lastButtonId: string;

  constructor(
    private subjectService: SubjectService,
    private global: Global,
    private calendarService: CalendarService
  ) {}

  ngOnInit(): void {
    this.calendarService.getActualWeek().subscribe((data) => {
      this.week = data.week;
      this.listOfSubjects();
      if (data.week == "A" || data.week == "B") {
        this.lastButtonId = data.week;
        var someElement = document.getElementById(data.week);
        someElement.classList.add("active-button");
      }
    });
    this.getAllEvents();
  }

  getAllEvents() {
    this.calendarService.getAllEvents(1).subscribe((data) => {
      data.forEach((obj) => {
        if (obj.title == "A") {
          this.events.push({
            start: subDays(startOfDay(new Date(obj.start)), 0),
            title: obj.title,
            color: colors.red,
          });
          
        }

        if (obj.title == "B") {
          this.events.push({
            start: subDays(startOfDay(new Date(obj.start)), 0),
            title: obj.title,
            color: colors.blue,
          });
        }

        if (obj.title == "FREE") {
          this.events.push({
            start: subDays(startOfDay(new Date(obj.start)), 0),
            title: obj.title,
            color: colors.yellow,
          });
        }
        this.triggerTodayClick();
      });
    });
  }

  triggerTodayClick() {
    let el: HTMLElement = this.myDiv.nativeElement;
    el.click();
  }

  listOfSubjects() {
    this.pageIndex = 0;
    this.pageSize = 100;
    if (this.global.isTeacher()) {
      this.subjectService
        .getAllSubjectsForTeacher(this.pageIndex, this.pageSize, this.week)
        .subscribe((data) => {
          this.subjectsList = data.content;
          this.subjectsListMonday = new Array();
          this.subjectsListTuesday = new Array();
          this.subjectsListWednesday = new Array();
          this.subjectsListThursday = new Array();
          this.subjectsListFriday = new Array();
          data.content.forEach((t) => {
            if (t.day == "MONDAY") {
              this.subjectsListMonday.push(t);
            }
            if (t.day == "TUESDAY") {
              this.subjectsListTuesday.push(t);
            }
            if (t.day == "WEDNESDAY") {
              this.subjectsListWednesday.push(t);
            }
            if (t.day == "THURSDAY") {
              this.subjectsListThursday.push(t);
            }
            if (t.day == "FRIDAY") {
              this.subjectsListFriday.push(t);
            }
          });
        });
    } else {
      this.subjectService
        .getAllSubjectsByUser(this.pageIndex, this.pageSize, this.week)
        .subscribe((data) => {
          this.subjectsList = data.content;
          this.subjectsListMonday = new Array();
          this.subjectsListTuesday = new Array();
          this.subjectsListWednesday = new Array();
          this.subjectsListThursday = new Array();
          this.subjectsListFriday = new Array();
          data.content.forEach((t) => {
            if (t.day == "MONDAY") {
              this.subjectsListMonday.push(t);
            }
            if (t.day == "TUESDAY") {
              this.subjectsListTuesday.push(t);
            }
            if (t.day == "WEDNESDAY") {
              this.subjectsListWednesday.push(t);
            }
            if (t.day == "THURSDAY") {
              this.subjectsListThursday.push(t);
            }
            if (t.day == "FRIDAY") {
              this.subjectsListFriday.push(t);
            }
          });
        });
    }
  }

  changeWeek(week: string) {
    this.week = week;
    this.global.setWeek(week);
    this.listOfSubjects();

    if (this.lastButtonId != undefined && this.lastButtonId != null) {
      var lastElement = document.getElementById(this.lastButtonId);
      lastElement.classList.remove("active-button");
    }
    var someElement = document.getElementById(week);
    someElement.classList.add("active-button");
    this.lastButtonId = week;
  }

  eventTimesChanged({
    event,
    newStart,
    newEnd,
  }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map((iEvent) => {
      if (iEvent === event) {
        return {
          ...event,
          start: newStart,
          end: newEnd,
        };
      }
      return iEvent;
    });
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }
}
