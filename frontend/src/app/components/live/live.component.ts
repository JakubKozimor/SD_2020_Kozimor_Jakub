import { Component, OnInit } from "@angular/core";
import { Classes } from "src/app/common/classes";
import { Global } from "src/app/global";
import { ClassesService } from "src/app/services/classes.service";

@Component({
  selector: "app-live",
  templateUrl: "./live.component.html",
  styleUrls: ["./live.component.css"],
})
export class LiveComponent implements OnInit {
  liveClasses: Classes[];
  isTeacher: boolean;

  constructor(private classesService: ClassesService, private global: Global) {}

  ngOnInit(): void {
    this.isTeacher = this.global.isTeacher();
    this.listOfLive();
  }

  listOfLive() {
    if (this.global.isTeacher()) {
      this.classesService
        .getLiveClassesForTeacher()
        .subscribe((data) => (this.liveClasses = data));
    } else {
      this.classesService
        .getLiveClasses()
        .subscribe((data) => (this.liveClasses = data));
    }
  }

  closeLive(classesId: number) {
    this.classesService.finishLive(classesId);
    window.location.reload();
  }
}
