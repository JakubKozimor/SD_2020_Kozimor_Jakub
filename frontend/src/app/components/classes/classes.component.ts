import { Component, OnInit, Input } from "@angular/core";
import { DomSanitizer, SafeResourceUrl } from "@angular/platform-browser";
import * as Stomp from "stompjs";
import * as SockJS from "sockjs-client";
import $ from "jquery";
import { ActivatedRoute } from "@angular/router";
import { Classes } from "src/app/common/classes";
import { ClassesService } from "src/app/services/classes.service";
import { FileServiceService } from "src/app/services/file-service.service";
import { LiveHomework } from "src/app/common/live-homework";
import { LiveHomeworkService } from "src/app/services/live-homework.service";
import { LiveHomeworksDetails } from "src/app/common/live-homeworks-details";
import { FormBuilder, FormGroup } from "@angular/forms";
import { LiveHomeworkAnswerFile } from "src/app/common/live-homework-answer-file";
import { LiveHomeworkAnswer } from "src/app/common/live-homework-answer";
import { LiveHomeworksAnswerService } from "src/app/services/live-homeworks-answer.service";
import { Global } from "src/app/global";

@Component({
  selector: "app-classes",
  templateUrl: "./classes.component.html",
  styleUrls: ["./classes.component.css"],
})
export class ClassesComponent implements OnInit {
  validateForm!: FormGroup;
  formSubmitted = false;
  homeworkFile: LiveHomeworkAnswerFile;
  tempFiles: LiveHomeworkAnswerFile[] = new Array();
  liveHomeworkAnswer: LiveHomeworkAnswer;

  actualUserAnswerDetails: LiveHomeworkAnswer;

  liveHomeworks: LiveHomework[];
  homeworksDetails: LiveHomeworksDetails;
  showDetailsBoolean = false;

  lastLiveHomeworkId: number;

  showAnswerForm = false;

  @Input()
  url: string = "";
  urlSafe: SafeResourceUrl;

  private serverUrl = "http://localhost:8080/socket";
  private title = "WebSockets chat";
  private stompClient;

  actualUserId: number;
  isTeacher: boolean;
  classes: Classes;
  videoUrl;
  constructor(
    private fileService: FileServiceService,
    private route: ActivatedRoute,
    public sanitizer: DomSanitizer,
    private classesService: ClassesService,
    private fb: FormBuilder,
    private liveHomeworkService: LiveHomeworkService,
    private liveHomeworksAnswerService: LiveHomeworksAnswerService,
    private global: Global
  ) {}

  ngOnInit() {
    this.isTeacher = this.global.isTeacher();
    let classesId = this.route.snapshot.paramMap.get("classesId");
    this.getLessonDetails(Number(classesId));
    this.initializeWebSocketConnection();
    this.actualUserId = Number(this.getAcctualUserId());
    this.getAllLivesHomeworks();
    this.validateForm = this.createLiveHomeworkAnswerForm();
  }

  getActualUserAnswer() {
    this.liveHomeworksAnswerService
      .getActualUserAnswerDetails(this.homeworksDetails.liveHomeworkId)
      .subscribe(
        (data) => {
          this.actualUserAnswerDetails = data;
        },
        (error) => {
          this.actualUserAnswerDetails = undefined;
        }
      );
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      this.liveHomeworkAnswer = this.validateForm.value;
      this.liveHomeworkAnswer.liveHomeworkId = this.homeworksDetails.liveHomeworkId;
      if (this.actualUserAnswerDetails != undefined) {
        this.liveHomeworkAnswer.liveHomeworkAnswerId = this.actualUserAnswerDetails.liveHomeworkAnswerId;
      }
      this.liveHomeworksAnswerService
        .addAnswer(this.liveHomeworkAnswer)
        .then((T) => this.getActualUserAnswer());
      this.validateForm.reset();
      this.tempFiles = new Array();
      this.showAnswerForm = false;
    } else {
      this.formSubmitted = false;
    }
  }

  createLiveHomeworkAnswerForm(): FormGroup {
    const form = this.fb.group({
      message: [null],
      files: [null],
    });
    return form;
  }

  onUpload(event) {
    let me = this;
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.homeworkFile = new LiveHomeworkAnswerFile();
      me.homeworkFile.fileName = String(event.target.files[0].name);
      me.homeworkFile.fileContent = String(reader.result);
      me.tempFiles.push(me.homeworkFile);
    };
    reader.onerror = function (error) {
      console.log("Error: ", error);
    };

    this.validateForm.controls["files"].setValue(this.tempFiles);
  }

  reset(fileNumber: LiveHomeworkAnswerFile) {
    const index: number = this.tempFiles.indexOf(fileNumber);
    if (index !== -1) {
      this.tempFiles.splice(index, 1);
    }
    this.validateForm.controls["files"].setValue(this.tempFiles);
  }

  addAnswerBoolean() {
    if (this.showAnswerForm) {
      this.showAnswerForm = false;
    } else {
      this.showAnswerForm = true;
    }
  }

  getAllLivesHomeworks() {
    let classesId = this.route.snapshot.paramMap.get("classesId");
    this.liveHomeworkService
      .getAllLiveHomeworks(Number(classesId))
      .subscribe((data) => {
        this.liveHomeworks = data;
      });
  }

  homeworkDetails(homeworkId: number) {
    if (this.showDetailsBoolean && this.lastLiveHomeworkId == homeworkId) {
      this.showDetailsBoolean = false;
    } else {
      this.lastLiveHomeworkId = homeworkId;
      this.liveHomeworkService
        .getHomeworkDetails(homeworkId)
        .subscribe((data) => {
          this.homeworksDetails = data;
          this.getActualUserAnswer();
        });
      this.showDetailsBoolean = true;
    }
  }

  initializeWebSocketConnection() {
    let classesId = this.route.snapshot.paramMap.get("classesId");
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe("/chat/" + classesId, (message) => {
        if (message.body) {
          let obj: ChatMessage = JSON.parse(message.body);
          if (obj.userId == Number(localStorage.getItem("user_id"))) {
            $(".chat").append(
              "<div style='font-weight: bold;' class='message'>" +
                obj.date +
                " - " +
                obj.userName +
                "<br>" +
                obj.message +
                "</div>"
            );
          } else {
            $(".chat").append(
              "<div class='message'>" +
                obj.date +
                " - " +
                obj.userName +
                "<br>" +
                obj.message +
                "</div>"
            );
          }
        }
      });
    });
  }

  getLessonDetails(classesId: number) {
    this.classesService.getClassesDetails(classesId).subscribe((data) => {
      this.classes = data;
      this.url = this.classes.url;
      this.urlSafe = this.sanitizer.bypassSecurityTrustResourceUrl(this.url);
    });
  }

  downloadFile(fileId: number, fileName: string) {
    this.fileService.downloadClassesFile(fileId, fileName);
  }

  downloadLiveFile(fileId: number, fileName: string) {
    this.fileService.downloadLiveHomeworkile(fileId, fileName);
  }

  downloadLiveAnswerFile(fileId: number, fileName: string) {
    this.fileService.downloadLiveHomeworkAnswerFile(fileId, fileName);
  }

  sendMessage(message) {
    if (message != "") {
      let classesId = this.route.snapshot.paramMap.get("classesId");
      let userId = Number(this.getAcctualUserId());
      this.stompClient.send(
        "/app/send/message/" + String(classesId) + `/${userId}`,
        {},
        message
      );
      $("#input").val("");
    }
  }

  getAcctualUserId() {
    return localStorage.getItem("user_id");
  }
}

export interface ChatMessage {
  userId: number;
  userName: string;
  date: string;
  message: string;
}
