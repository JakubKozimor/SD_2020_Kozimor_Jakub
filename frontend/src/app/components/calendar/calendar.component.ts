import { Component, OnInit } from '@angular/core';
import { SubjectService } from 'src/app/services/subject.service';
import { Subject } from 'src/app/common/subject';


@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  pageIndex: number;
  pageSize: number;
  subjectsList: Subject[];

  constructor(private subjectService: SubjectService) { }

  ngOnInit(): void {
    this.listOfSubjects();
  }

  listOfSubjects() {
    this.pageIndex = 0;
    this.pageSize = 100;
    this.subjectService.getAllSubjectsByUser(this.pageIndex, this.pageSize).subscribe(data => this.subjectsList = data.content)
  }

}
