import { Component, OnInit } from '@angular/core';
import { Homework } from 'src/app/common/homework';
import { HomeworkService } from 'src/app/services/homework.service';

@Component({
  selector: 'app-homework',
  templateUrl: './homework.component.html',
  styleUrls: ['./homework.component.css']
})
export class HomeworkComponent implements OnInit {
  pageIndex: number;
  pageSize: number;
  homeworkList: Homework[];
  today: Date;
  daysToDeadline: number;

  constructor(private homeworkService: HomeworkService) { }

  ngOnInit(): void {
    this.listOfHomework();
  }

  listOfHomework() {
    this.pageIndex = 0;
    this.pageSize = 10;
    this.homeworkService.getAllActiveHomeworkByUser(this.pageIndex, this.pageSize).subscribe(data => this.homeworkList = data.content)
  }

  countTime(deadline: Date) {
    this.today = new Date;
    this.daysToDeadline = Number(String(deadline).substr(8, 2)) - this.today.getDate()
    if (this.daysToDeadline < 2) {
      return this.daysToDeadline + " Dzień"
    } else {
      return this.daysToDeadline + " Dni"
    }

  }

}
