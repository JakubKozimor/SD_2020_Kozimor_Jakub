import { 
  Component,
   OnInit,
   ViewChild,
  TemplateRef,
  ElementRef,
  } 
   from '@angular/core';
   import {
    startOfDay,
    subDays,
  } from 'date-fns';
  import {
    CalendarEvent,
    CalendarEventAction,
    CalendarEventTimesChangedEvent,
    CalendarView,
  } from 'angular-calendar';
import { SubjectService } from 'src/app/services/subject.service';
import { Subject } from 'src/app/common/subject';
import { Global } from 'src/app/global';
import { CalendarService } from 'src/app/services/calendar.service';
import { ActivatedRoute } from '@angular/router';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  pageIndex: number;
  pageSize: number;
  subjectsList: Subject[];

  week: string;

  @ViewChild('myDiv') myDiv: ElementRef<HTMLElement>;
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
      a11yLabel: 'Edit',
      onClick: ({ event }: { event: CalendarEvent }): void => {
      },
    },
    {
      label: '<i class="fas fa-fw fa-trash-alt"></i>',
      a11yLabel: 'Delete',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
      },
    },
  ];

  // refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [];
  newevents: CalendarEvent[] = [];

  activeDayIsOpen: boolean = true;

  
  constructor(
    private subjectService: SubjectService,
    private global: Global,
    private route: ActivatedRoute,
    private calendarService: CalendarService) { }

  ngOnInit(): void {
    this.calendarService.getActualWeek().subscribe(data => {
      this.week = data.week;
      this.listOfSubjects();
    });
    this.getAllEvents();
  }

  getAllEvents(){
    this.calendarService.getAllEvents(1).subscribe(data => {
      data.forEach(obj => {
        if(obj.title == 'A'){
          this.events.push({
            start: subDays(startOfDay(new Date(obj.start)), 0),
            title: obj.title,
            color: colors.red,
          })
        }

        if(obj.title == 'B'){
          this.events.push({
            start: subDays(startOfDay(new Date(obj.start)), 0),
            title: obj.title,
            color: colors.blue,
          })
        }

        if(obj.title == 'FREE'){
          this.events.push({
            start: subDays(startOfDay(new Date(obj.start)), 0),
            title: obj.title,
            color: colors.yellow,
          }
          )
        }
        this.triggerTodayClick();
      })
    });
    
  }

  triggerTodayClick() {
    let el: HTMLElement = this.myDiv.nativeElement;
    el.click();
}

  listOfSubjects() {
    this.pageIndex = 0;
    this.pageSize = 100;
    this.subjectService.getAllSubjectsByUser(this.pageIndex, this.pageSize, this.week).subscribe(data => this.subjectsList = data.content)
  }

  changeWeek(week: string) {
    this.week = week;
    this.global.setWeek(week);
    this.listOfSubjects();
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
