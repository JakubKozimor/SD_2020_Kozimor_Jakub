import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { LiveHomeworkAnswerDetailsDto } from "src/app/common/live-homework-answer-details-dto";
import { FileServiceService } from "src/app/services/file-service.service";
import { LiveHomeworksAnswerService } from "src/app/services/live-homeworks-answer.service";

@Component({
  selector: "app-all-live-homework-answers-for-teacher",
  templateUrl: "./all-live-homework-answers-for-teacher.component.html",
  styleUrls: ["./all-live-homework-answers-for-teacher.component.css"],
})
export class AllLiveHomeworkAnswersForTeacherComponent implements OnInit {
  allAnsers: LiveHomeworkAnswerDetailsDto[];

  constructor(
    private route: ActivatedRoute,
    private liveHomeworksAnswerService: LiveHomeworksAnswerService,
    private fileService: FileServiceService
  ) {}

  ngOnInit(): void {
    this.liveHomeworkAnswers();
  }

  liveHomeworkAnswers() {
    let liveHomeworkId = Number(
      this.route.snapshot.paramMap.get("liveHomeworkId")
    );
    this.liveHomeworksAnswerService
      .getAllLiveHomeworkAnswers(liveHomeworkId)
      .subscribe((data) => {
        this.allAnsers = data;
        console.log(data);
      });
  }

  downloadLiveHomeworkFile(fileId: number, fileName: string) {
    this.fileService.downloadLiveHomeworkAnswerFile(fileId, fileName);
  }
}
