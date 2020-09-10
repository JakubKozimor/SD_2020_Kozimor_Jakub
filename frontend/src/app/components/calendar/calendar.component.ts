import { Component, OnInit } from '@angular/core';
import { SubjectService } from 'src/app/services/subject.service';
import { Subject } from 'src/app/common/subject';
import { Router } from '@angular/router';


@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {

  subjectsList: Subject[];

  constructor(private subjectService: SubjectService) { }

  ngOnInit(): void {
    this.listOfSubjects();
  }

  listOfSubjects() {
    this.subjectService.getAllSubjectsByUser(1).subscribe(data => this.subjectsList = data)
  }

}
