import { Component, OnInit } from '@angular/core';
import { Teacher } from 'src/app/common/teacher';
import { TeacherService } from 'src/app/services/teacher.service';

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {
  pageIndex: number;
  pageSize: number;
  teacherList: Teacher[];

  constructor(private teacherService: TeacherService) { }

  ngOnInit(): void {
    this.listOfTeachers();
  }

  listOfTeachers() {
    this.pageIndex = 0;
    this.pageSize = 10;
    this.teacherService.getAllTeachersByUser(this.pageIndex, this.pageSize).subscribe(data => this.teacherList = data.content)
  }

}
