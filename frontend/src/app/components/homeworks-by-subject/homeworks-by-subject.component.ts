import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Homework } from 'src/app/common/homework';
import { RatedHomework } from 'src/app/common/rated-homework';
import { HomeworkService } from 'src/app/services/homework.service';

@Component({
  selector: 'app-homeworks-by-subject',
  templateUrl: './homeworks-by-subject.component.html',
  styleUrls: ['./homeworks-by-subject.component.css']
})
export class HomeworksBySubjectComponent implements OnInit {

  today: Date;
  daysToDeadline: number;

  subjectId: number;

  doneHomeworkList: Homework[];
  ratedHomeworkList: RatedHomework[];
  activeHomeworkList: Homework[];
  lateHomeworkList: Homework[];

  theDoneHomeworkPageNumber: number = 1;
  theDoneHomeworkPageSize: number = 5;
  theDoneHomeworkTotalElements: number = 0;

  theRatedHomeworkPageNumber: number = 1;
  theRatedHomeworkPageSize: number = 5;
  theRatedHomeworkTotalElements: number = 0;

  theActiveHomeworkPageNumber: number = 1;
  theActiveHomeworkPageSize: number = 5;
  theActiveHomeworkTotalElements: number = 0;

  theLateHomeworkPageNumber: number = 1;
  theLateHomeworkPageSize: number = 5;
  theLateHomeworkTotalElements: number = 0;

  constructor(
    private route: ActivatedRoute,
    private homeworkService: HomeworkService) { }

  ngOnInit(): void {
    this.subjectId = Number(this.route.snapshot.paramMap.get('subjectId'));
    this.listOfDoneHomework();
    this.listOfRatedHomework();
    this.listOfActiveHomework();
    this.listOfLateHomework();
  }

  isBeforeDeadline(deadline: Date): boolean {
    let today = new Date;
    today.setDate(today.getDate() - 1)
    let deadlineInDate = new Date(deadline);
    return today < deadlineInDate;
  }

  listOfDoneHomework() {
    this.homeworkService.getAllDoneHomeworkByUser(this.theDoneHomeworkPageNumber - 1, this.theDoneHomeworkPageSize, this.subjectId).subscribe(data => {
      this.doneHomeworkList = data.content;
      this.theDoneHomeworkPageNumber = data.number + 1;
      this.theDoneHomeworkPageSize = data.size;
      this.theDoneHomeworkTotalElements = data.totalElements;
    })
  }

  listOfRatedHomework() {
    this.homeworkService.getAllRatedHomeworkByUser(this.theRatedHomeworkPageNumber - 1, this.theRatedHomeworkPageSize, this.subjectId).subscribe(data => {
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

  listOfActiveHomework() {
    this.homeworkService.getAllActiveHomeworkByUser(this.theActiveHomeworkPageNumber - 1, this.theActiveHomeworkPageSize, this.subjectId).subscribe(data => {
      this.activeHomeworkList = data.content;
      this.theActiveHomeworkPageNumber = data.number + 1;
      this.theActiveHomeworkPageSize = data.size;
      this.theActiveHomeworkTotalElements = data.totalElements;
    })
  }

  listOfLateHomework() {
    this.homeworkService.getAllLateHomeworkByUser(this.theLateHomeworkPageNumber - 1, this.theLateHomeworkPageSize, this.subjectId).subscribe(data => {
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
