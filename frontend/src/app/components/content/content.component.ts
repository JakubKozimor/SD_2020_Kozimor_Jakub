import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/common/subject';
import { SubjectService } from 'src/app/services/subject.service';
import { HomeworkService } from 'src/app/services/homework.service';
import { Homework } from 'src/app/common/homework';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {

  subjectsList: Subject[];
  homeworkList: Homework[];

  hour: number;
  minute: number;
  subjectTime: number;
  
  constructor(private subjectService: SubjectService, private homeworkService: HomeworkService) { }

  ngOnInit(): void {
    this.listOfSubjects();
    this.listOfHomework();
  }
  listOfSubjects() {
    this.subjectService.getFiveFirstSubjectsByUser(1).subscribe(data => this.subjectsList = data)
  }

  listOfHomework(){
    this.homeworkService.getAllSubjectsByUser(1).subscribe(data => this.homeworkList = data.content)
  }

  countTime(startTime: string, longOfTime: string){
    this.hour = Number(startTime.substr(0,2));
    this.minute = Number(startTime.substr(3,4));
    this.subjectTime = Number(longOfTime);
    this.minute += this.subjectTime;

    while(this.minute >= 60){
      this.minute -= 60;
      this.hour += 1;
    }

    if(this.minute < 10){
      return String(this.hour) + ":0" + this.minute;
    } else{
      return String(this.hour) + ":" + this.minute;
    }
  }

  getDay(day: string){
    if (day == 'MONDAY'){
      return "Poniedziałek"
    }
    if (day == 'TUESDAY'){
      return "Wtorek"
    }
    if (day == 'WEDNESDAY'){
      return "Środa"
    }
    if (day == 'THURSDAY'){
      return "Czwartek"
    }
    if (day == 'FRIDAY'){
      return "Piątek"
    }
  }
}
