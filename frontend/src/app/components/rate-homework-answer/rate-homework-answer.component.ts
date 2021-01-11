import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { HomeworkAnswerDetails } from "src/app/common/homework-answer-details";
import { FileServiceService } from "src/app/services/file-service.service";
import { HomeworkAnswerService } from "src/app/services/homework-answer.service";
import { Location } from "@angular/common";
@Component({
  selector: "app-rate-homework-answer",
  templateUrl: "./rate-homework-answer.component.html",
  styleUrls: ["./rate-homework-answer.component.css"],
})
export class RateHomeworkAnswerComponent implements OnInit {
  homeworkAnswerDetails: HomeworkAnswerDetails;

  constructor(
    private route: ActivatedRoute,
    private fileService: FileServiceService,
    private homeworkAnswerService: HomeworkAnswerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getHomeworkAnswerDetails();
  }

  getHomeworkAnswerDetails() {
    let answerId = Number(this.route.snapshot.paramMap.get("homeworkAnswer"));
    this.homeworkAnswerService
      .getHomeworkAnswerDetails(answerId)
      .subscribe((data) => {
        this.homeworkAnswerDetails = data;
      });
  }

  downloadHomeworkAnswerFile(fileId: number, fileName: string) {
    this.fileService.downloadHomeworkAnswerFile(fileId, fileName);
  }

  addGrade(homeworkAnswerId: number, grade: string, comment: string) {
    if (grade != "null") {
      this.homeworkAnswerService.addGrade(homeworkAnswerId, grade, comment);
      setTimeout(() => {
        this.router.navigate([
          "viewHomework/" + this.route.snapshot.paramMap.get("homeworkId"),
        ]);
      }, 500);
    }
  }
}
