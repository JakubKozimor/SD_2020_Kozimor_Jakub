import { Component, OnInit } from '@angular/core';
import { Teacher } from 'src/app/common/teacher';
import { TeacherService } from 'src/app/services/teacher.service';

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {
  teacherList: Teacher[];

  thePageNumber: number = 1;
  thePageSize: number = 5;
  theElements: number = 0;

  constructor(private teacherService: TeacherService) { }

  ngOnInit(): void {
    this.listOfTeachers();
  }

  listOfTeachers() {
    this.teacherService.getAllTeachersByUser(this.thePageNumber - 1, this.thePageSize).subscribe(data => {
      this.teacherList = data.content;
      this.thePageNumber = data.number + 1;
      this.thePageSize = data.size;
      this.theElements = data.totalElements;
    })
  }

  updateQuantity(pageSize: number) {
    this.thePageSize = pageSize;
    this.thePageNumber = 1;
    this.listOfTeachers();
  }

}
