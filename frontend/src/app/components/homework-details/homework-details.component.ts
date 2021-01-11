import { DatePipe } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { HomeworkAnswerDto } from "src/app/common/homework-answer-dto";
import { HomeworkDetails } from "src/app/common/homework-details";
import { Global } from "src/app/global";
import { FileServiceService } from "src/app/services/file-service.service";
import { HomeworkAnswerService } from "src/app/services/homework-answer.service";
import { HomeworkService } from "src/app/services/homework.service";

@Component({
  selector: "app-homework-details",
  templateUrl: "./homework-details.component.html",
  styleUrls: ["./homework-details.component.css"],
})
export class HomeworkDetailsComponent implements OnInit {
  homeworkDetails: HomeworkDetails;

  homeworkAnswerList: HomeworkAnswerDto[];

  homeworkAnswerListWithGrade: HomeworkAnswerDto[];

  isTeacher: boolean;

  homeworkId: number;
  thePageNumber: number = 1;
  thePageSize: number = 5;
  theElements: number = 0;

  thePageNumberWithGrade: number = 1;
  thePageSizeWithGrade: number = 5;
  theElementsWithGrade: number = 0;

  constructor(
    private homeworkService: HomeworkService,
    private route: ActivatedRoute,
    private fileService: FileServiceService,
    private homeworkAnswerService: HomeworkAnswerService,
    private datePipe: DatePipe,
    private global: Global,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.isTeacher = this.global.isTeacher();
    let homeworkId = this.route.snapshot.paramMap.get("homeworkId");
    this.getHomeworkDetails(Number(homeworkId));
    this.getHomeworkAnswers(Number(homeworkId));
    this.getHomeworkAnswersWithGrade(Number(homeworkId));
    this.homeworkId = Number(homeworkId);
  }

  getHomeworkDetails(homeworkId: number) {
    this.homeworkService
      .getHomeworkDetails(homeworkId)
      .subscribe((data) => (this.homeworkDetails = data));
  }

  getHomeworkAnswers(homeworkId: number) {
    this.homeworkAnswerService
      .getAllHomeworkAnswers(
        this.thePageNumber - 1,
        this.thePageSize,
        homeworkId
      )
      .subscribe((data) => {
        this.homeworkAnswerList = data.content;
        this.thePageNumber = data.number + 1;
        this.thePageSize = data.size;
        this.theElements = data.totalElements;
      });
  }

  getHomeworkAnswersWithGrade(homeworkId: number) {
    this.homeworkAnswerService
      .getAllHomeworkAnswersWithGrade(
        this.thePageNumberWithGrade - 1,
        this.thePageSizeWithGrade,
        homeworkId
      )
      .subscribe((data) => {
        this.homeworkAnswerListWithGrade = data.content;
        this.thePageNumberWithGrade = data.number + 1;
        this.thePageSizeWithGrade = data.size;
        this.theElementsWithGrade = data.totalElements;
      });
  }

  updateQuantity(pageSize: number) {
    this.thePageSize = pageSize;
    this.thePageNumber = 1;
    this.getHomeworkAnswers(this.homeworkId);
  }

  updateQuantityWithGrade(pageSize: number) {
    this.thePageSizeWithGrade = pageSize;
    this.thePageNumberWithGrade = 1;
    this.getHomeworkAnswersWithGrade(this.homeworkId);
  }

  downloadFile(fileId: number, fileName: string) {
    this.fileService.downloadHomeworkFile(fileId, fileName);
  }

  transformDate(date) {
    return this.datePipe.transform(date, "yyyy-MM-dd");
  }

  ratePage(homeworkAnswerId: number) {
    this.router.navigate(["rate/" + homeworkAnswerId + "/" + this.route.snapshot.paramMap.get("homeworkId")]);
  }
}
