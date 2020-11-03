import { Component, OnInit } from '@angular/core';
import { Homework } from 'src/app/common/homework';
import { HomeworkService } from 'src/app/services/homework.service';

@Component({
  selector: 'app-teacher-homework',
  templateUrl: './teacher-homework.component.html',
  styleUrls: ['./teacher-homework.component.css']
})
export class TeacherHomeworkComponent implements OnInit {

  notRatedHomeworkList: Homework[];
  ratedHomeworkList: Homework[];
  activeHomeworkList: Homework[];


  notRatedHomeworkPageNumber: number = 1;
  notRatedHomeworkPageSize: number = 5;
  notRatedHomeworkTotalElements: number = 0;

  ratedHomeworkPageNumber: number = 1;
  ratedHomeworkPageSize: number = 5;
  ratedHomeworkTotalElements: number = 0;

  theActiveHomeworkPageNumber: number = 1;
  theActiveHomeworkPageSize: number = 5;
  theActiveHomeworkTotalElements: number = 0;

  constructor(private homeworkService: HomeworkService) { }

  ngOnInit(): void {
    this.listOfNotRatedHomeworks();
    this.listRatedHomeworks();
    this.listOfActiveHomework();
  }

  listOfActiveHomework() {
    this.homeworkService.getAllActiveHomeworksForTeacher(this.theActiveHomeworkPageNumber - 1, this.theActiveHomeworkPageSize).subscribe(data => {
      this.activeHomeworkList = data.content;
      this.theActiveHomeworkPageNumber = data.number + 1;
      this.theActiveHomeworkPageSize = data.size;
      this.theActiveHomeworkTotalElements = data.totalElements;
    })
  }

  listOfNotRatedHomeworks() {
    this.homeworkService.getAllNotRatedHomeworks(this.notRatedHomeworkPageNumber - 1, this.notRatedHomeworkPageSize).subscribe(data => {
      this.notRatedHomeworkList = data.content;
      this.notRatedHomeworkPageNumber = data.number + 1;
      this.notRatedHomeworkPageSize = data.size;
      this.notRatedHomeworkTotalElements = data.totalElements;
    })
  }

  listRatedHomeworks() {
    this.homeworkService.getAllRatedHomeworks(this.ratedHomeworkPageNumber - 1, this.ratedHomeworkPageSize).subscribe(data => {
      this.ratedHomeworkList = data.content;
      this.ratedHomeworkPageNumber = data.number + 1;
      this.ratedHomeworkPageSize = data.size;
      this.ratedHomeworkTotalElements = data.totalElements;
    })
  }

  updateNotRatedHomeworksQuantity(pageSize: number) {
    this.notRatedHomeworkPageSize = pageSize;
    this.notRatedHomeworkPageNumber = 1;
    this.listOfNotRatedHomeworks();
  }

  updateRatedHomeworksQuantity(pageSize: number) {
    this.ratedHomeworkPageSize = pageSize;
    this.ratedHomeworkPageNumber = 1;
    this.listRatedHomeworks();
  }

  updateActiveHomeworksQuantity(pageSize: number) {
    this.theActiveHomeworkPageSize = pageSize;
    this.theActiveHomeworkPageNumber = 1;
    this.listOfActiveHomework();
  }


}
