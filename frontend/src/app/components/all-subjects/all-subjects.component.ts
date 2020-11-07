import { Component, OnInit } from "@angular/core";
import { Subject } from "src/app/common/subject";
import { SubjectFile } from "src/app/common/subject-file";
import { Global } from "src/app/global";
import { FileServiceService } from "src/app/services/file-service.service";
import { SubjectService } from "src/app/services/subject.service";

@Component({
  selector: "app-all-subjects",
  templateUrl: "./all-subjects.component.html",
  styleUrls: ["./all-subjects.component.css"],
})
export class AllSubjectsComponent implements OnInit {
  subjectsList: Subject[];
  tempSubjectsFiles: SubjectFile[];

  showFilesBoolean = false;
  lastFileId: number;
  isTeacher: boolean;

  thePageNumber: number = 1;
  thePageSize: number = 5;
  theElements: number = 0;

  actualSubject: Subject;
  lastButtonId = "1";

  constructor(
    private subjectService: SubjectService,
    private fileService: FileServiceService,
    private global: Global
  ) {}

  ngOnInit(): void {
    this.isTeacher = this.global.isTeacher();
    this.listOfSubjects();
  }

  showFiles(subjectId: number) {
    if (this.showFilesBoolean && this.lastFileId == subjectId) {
      this.showFilesBoolean = false;
    } else {
      this.lastFileId = subjectId;
      let subject = this.subjectsList.find((i) => i.subjectId === subjectId);
      this.actualSubject = subject;
      this.tempSubjectsFiles = subject.files;
      this.showFilesBoolean = true;
    }

    var lastElement = document.getElementById(this.lastButtonId);
    lastElement.classList.remove("active-button");

    var someElement = document.getElementById(String(subjectId));
    someElement.classList.add("active-button");
    this.lastButtonId = String(subjectId);
  }

  filesPresent(): boolean {
    return this.tempSubjectsFiles.length < 1;
  }

  listOfSubjects() {
    if (this.global.isTeacher()) {
      this.subjectService
        .getAllSubjectsForTeacher(
          this.thePageNumber - 1,
          this.thePageSize,
          "ALL"
        )
        .subscribe((data) => {
          this.subjectsList = data.content;
          this.thePageNumber = data.number + 1;
          this.thePageSize = data.size;
          this.theElements = data.totalElements;
        });
    } else {
      this.subjectService
        .getAllSubjectsByUser(this.thePageNumber - 1, this.thePageSize, "ALL")
        .subscribe((data) => {
          this.subjectsList = data.content;
          this.thePageNumber = data.number + 1;
          this.thePageSize = data.size;
          this.theElements = data.totalElements;
        });
    }
  }

  updateQuantity(pageSize: number) {
    this.thePageSize = pageSize;
    this.thePageNumber = 1;
    this.listOfSubjects();
  }

  downloadSubjectFile(fileId: number, fileName: string) {
    this.fileService.downloadSubjectFile(fileId, fileName);
  }

  getDay(day: string) {
    if (day == "MONDAY") {
      return "Poniedziałek";
    }
    if (day == "TUESDAY") {
      return "Wtorek";
    }
    if (day == "WEDNESDAY") {
      return "Środa";
    }
    if (day == "THURSDAY") {
      return "Czwartek";
    }
    if (day == "FRIDAY") {
      return "Piątek";
    }
  }
}
