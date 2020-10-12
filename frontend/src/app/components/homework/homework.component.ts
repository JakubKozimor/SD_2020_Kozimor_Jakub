import { Component, OnInit } from '@angular/core';
import { Homework } from 'src/app/common/homework';
import { HomeworkService } from 'src/app/services/homework.service';

@Component({
  selector: 'app-homework',
  templateUrl: './homework.component.html',
  styleUrls: ['./homework.component.css']
})
export class HomeworkComponent implements OnInit {
  activeHomeworkList: Homework[];
  lateHomeworkList: Homework[];

  today: Date;
  daysToDeadline: number;

  theActiveHomeworkPageNumber: number = 1;
  theActiveHomeworkPageSize: number = 5;
  theActiveHomeworkTotalElements: number = 0;

  theLateHomeworkPageNumber: number = 1;
  theLateHomeworkPageSize: number = 5;
  theLateHomeworkTotalElements: number = 0;

  constructor(private homeworkService: HomeworkService) { }

  ngOnInit(): void {
    this.listOfActiveHomework();
    this.listOfLateHomework();
  }

  listOfActiveHomework() {
    this.homeworkService.getAllActiveHomeworkByUser(this.theActiveHomeworkPageNumber - 1, this.theActiveHomeworkPageSize, 0).subscribe(data => {
      this.activeHomeworkList = data.content;
      this.theActiveHomeworkPageNumber = data.number + 1;
      this.theActiveHomeworkPageSize = data.size;
      this.theActiveHomeworkTotalElements = data.totalElements;
    })
  }

  listOfLateHomework() {
    this.homeworkService.getAllLateHomeworkByUser(this.theLateHomeworkPageNumber - 1, this.theLateHomeworkPageSize, 0).subscribe(data => {
      this.lateHomeworkList = data.content;
      this.theLateHomeworkPageNumber = data.number + 1;
      this.theLateHomeworkPageSize = data.size;
      this.theLateHomeworkTotalElements = data.totalElements;
    })
  }

  countTime(deadline: Date) {
    this.today = new Date;
    let deadlineInDate = new Date(deadline);
    this.daysToDeadline = Math.round(this.getDifferenceInDays(this.today, deadlineInDate)) + 1;
    if (this.daysToDeadline == 1) {
      return this.daysToDeadline + " Dzień"
    } else {
      return this.daysToDeadline + " Dni"
    }

  }
  getDifferenceInDays(date1, date2) {
    const diffInMs = Math.abs(date2 - date1);
    return diffInMs / (1000 * 60 * 60 * 24);
  }



  updateActiveHomeworksQuantity(pageSize: number) {
    this.theActiveHomeworkPageSize = pageSize;
    this.theActiveHomeworkPageNumber = 1;
    this.listOfActiveHomework();
  }

  updateLateHomeworksQuantity(pageSize: number) {
    this.theLateHomeworkPageSize = pageSize;
    this.theLateHomeworkPageNumber = 1;
    this.listOfLateHomework();
  }
}
