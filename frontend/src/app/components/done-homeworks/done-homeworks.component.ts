import { Component, OnInit } from '@angular/core';
import { Homework } from 'src/app/common/homework';
import { RatedHomework } from 'src/app/common/rated-homework';
import { HomeworkService } from 'src/app/services/homework.service';

@Component({
  selector: 'app-done-homeworks',
  templateUrl: './done-homeworks.component.html',
  styleUrls: ['./done-homeworks.component.css']
})
export class DoneHomeworksComponent implements OnInit {

  doneHomeworkList: Homework[];
  ratedHomeworkList: RatedHomework[];

  theDoneHomeworkPageNumber: number = 1;
  theDoneHomeworkPageSize: number = 5;
  theDoneHomeworkTotalElements: number = 0;

  theRatedHomeworkPageNumber: number = 1;
  theRatedHomeworkPageSize: number = 5;
  theRatedHomeworkTotalElements: number = 0;

  constructor(
    private homeworkService: HomeworkService) { }

  ngOnInit(): void {
    this.listOfDoneHomework();
    this.listOfRatedHomework();
  }

  isBeforeDeadline(deadline: Date): boolean {
    let today = new Date;
    today.setDate(today.getDate()-1)
    let deadlineInDate = new Date(deadline);
    return today < deadlineInDate;
  }

  listOfDoneHomework() {
    this.homeworkService.getAllDoneHomeworkByUser(this.theDoneHomeworkPageNumber - 1, this.theDoneHomeworkPageSize, 0).subscribe(data => {
      this.doneHomeworkList = data.content;
      this.theDoneHomeworkPageNumber = data.number + 1;
      this.theDoneHomeworkPageSize = data.size;
      this.theDoneHomeworkTotalElements = data.totalElements;
    })
  }

  listOfRatedHomework() {
    this.homeworkService.getAllRatedHomeworkByUser(this.theRatedHomeworkPageNumber - 1, this.theRatedHomeworkPageSize, 0).subscribe(data => {
      this.ratedHomeworkList = data.content;
      this.theRatedHomeworkPageNumber = data.number + 1;
      this.theRatedHomeworkPageSize = data.size;
      this.theRatedHomeworkTotalElements = data.totalElements;
    })
  }

  updateDoneHomeworksQuantity(pageSize: number) {
    this.theDoneHomeworkPageSize = pageSize;
    this.theDoneHomeworkPageNumber = 1;
    this.listOfDoneHomework();
  }

  updateRatedHomeworksQuantity(pageSize: number) {
    this.theRatedHomeworkPageSize = pageSize;
    this.theRatedHomeworkPageNumber = 1;
    this.listOfRatedHomework();
  }

}
