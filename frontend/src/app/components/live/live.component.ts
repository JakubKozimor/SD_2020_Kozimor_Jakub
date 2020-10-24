import { Component, OnInit } from '@angular/core';
import { Classes } from 'src/app/common/classes';
import { ClassesService } from 'src/app/services/classes.service';

@Component({
  selector: 'app-live',
  templateUrl: './live.component.html',
  styleUrls: ['./live.component.css']
})
export class LiveComponent implements OnInit {

  liveClasses: Classes[];

  constructor(
    private classesService: ClassesService
  ) { }

  ngOnInit(): void {
    this.listOfLive();
  }

  listOfLive(){
    this.classesService.getLiveClasses().subscribe(data => this.liveClasses = data)
  }

}
