import {
  Component,
  OnInit,
  ViewChild,
  TemplateRef,
  ElementRef,
} from '@angular/core';
import {
  startOfDay,
  subDays,
} from 'date-fns';
import { Subject } from 'rxjs';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
} from 'angular-calendar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CalendarService } from 'src/app/services/calendar.service';
import { NewEvent } from 'src/app/common/new-event';
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
  selector: 'app-add-schedule',
  templateUrl: './add-schedule.component.html',
  styleUrls: ['./add-schedule.component.css'],
})
export class AddScheduleComponent implements OnInit {

  validateForm!: FormGroup;
  formSubmitted = false;
  schooltId: number;
  @ViewChild('myDiv') myDiv: ElementRef<HTMLElement>;
  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any>;

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

  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [];
  newevents: CalendarEvent[] = [];

  activeDayIsOpen: boolean = true;


  constructor(
    private fb: FormBuilder,
    private calendarService: CalendarService,
    private route: ActivatedRoute
  ) {
    
  }

  ngOnInit(): void {
    this.schooltId = Number(this.route.snapshot.paramMap.get("schoolId"));
    this.validateForm = this.createWeekForm();
    this.getAllEvents();
  }

  getAllEvents(){
    this.calendarService.getAllEvents(this.schooltId).subscribe(data => {
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
        
      })
    });
    this.triggerTodayClick();
  }

  triggerTodayClick() {
    let el: HTMLElement = this.myDiv.nativeElement;
    el.click();
}

  createWeekForm(): FormGroup {
    const form = this.fb.group({
      start: [null, [Validators.required]],
      end: [null, [Validators.required]],
      week: [null, [Validators.required]],
    });
    return form;
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      let newEvent = new NewEvent();
      newEvent.title = this.week.value;
      newEvent.start = new Date(this.parseDate(this.start.value));
      newEvent.end = new Date(this.parseDate(this.end.value));
      this.calendarService.addEvent(newEvent, this.schooltId);
      this.validateForm.reset();
      this.events = [];
      window.location.reload();
    } else {
      this.formSubmitted = false;
    }
  }

  parseDate(date): string {
    let year = date.year;
    let month = date.month;
    let day = date.day;
    if (month < 10) {
      month = "0" + month;
    }
    if (day < 10) {
      day = "0" + day;
    }
    return year + "-" + month + "-" + day;
  }

  get start(): any {
    return this.validateForm.get('start');
  }

  get end(): any {
    return this.validateForm.get('end');
  }

  get week(): any {
    return this.validateForm.get('week');
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
