import { DatePipe } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { NgbDate } from "@ng-bootstrap/ng-bootstrap";
import { HomeworkDetails } from "src/app/common/homework-details";
import { HomeworkFile } from "src/app/common/homework-file";
import { MessageDetails } from "src/app/common/message-details";
import { HomeworkService } from "src/app/services/homework.service";

@Component({
  selector: "app-update-homework",
  templateUrl: "./update-homework.component.html",
  styleUrls: ["./update-homework.component.css"],
})
export class UpdateHomeworkComponent implements OnInit {
  homeworkDetails: HomeworkDetails;

  

  validateForm!: FormGroup;
  formSubmitted = false;

  homeworkFile: HomeworkFile;

  tempFiles: HomeworkFile[] = new Array();

  constructor(
    private homeworkService: HomeworkService,
    private route: ActivatedRoute,
    private datePipe: DatePipe,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    let homeworkId = this.route.snapshot.paramMap.get("homeworkId");
    this.getHomeworkDetails(Number(homeworkId));
    this.validateForm = this.createHomeworkForm();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      let homeworkId = this.route.snapshot.paramMap.get("homeworkId");
      this.validateForm.controls["deadline"].setValue(
        this.parseDate(this.deadline.value)
      );
      this.homeworkService.updateHomework(
        this.validateForm.value,
        Number(homeworkId)
      );
      this.validateForm.reset();
      this.tempFiles = new Array();
      this.changePage();
    } else {
      this.formSubmitted = false;
    }
  }

  changePage() {
    this.router.navigate(["homework"]);
  }

  parseDate(date): string {
    let year = date.year;
    let month = date.month;
    let day = date.day;
    if (month < 10) {
      month = "0" + month;
    }
    if (day < 10) {
      day = "0" + day;
    }
    return year + "-" + month + "-" + day;
  }

  createHomeworkForm(): FormGroup {
    const form = this.fb.group({
      title: [null, [Validators.required]],
      description: [null, [Validators.required]],
      deadline: [null, [Validators.required]],
      files: [null],
    });
    return form;
  }

  get title(): any {
    return this.validateForm.get("title");
  }

  get description(): any {
    return this.validateForm.get("description");
  }
  get files(): any {
    return this.validateForm.get("files");
  }
  get status(): any {
    return this.validateForm.get("status");
  }
  get deadline(): any {
    return this.validateForm.get("deadline");
  }

  mapForm() {
    this.validateForm.controls["files"].setValue(this.homeworkDetails.files);
    this.validateForm.controls["description"].setValue(
      this.homeworkDetails.description
    );
    let date: Date = new Date(
      this.transformDate(this.homeworkDetails.deadline)
    );
    this.validateForm.controls["deadline"].setValue(
      new NgbDate(date.getFullYear(), date.getMonth() + 1, date.getDate())
    );
    this.validateForm.controls["title"].setValue(this.homeworkDetails.title);
    this.tempFiles = this.homeworkDetails.files;
  }

  onUpload(event) {
    let me = this;
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.homeworkFile = new HomeworkFile();
      me.homeworkFile.fileName = String(event.target.files[0].name);
      me.homeworkFile.fileContent = String(reader.result);
      me.tempFiles.push(me.homeworkFile);
    };
    reader.onerror = function (error) {
      console.log("Error: ", error);
    };

    this.validateForm.controls["files"].setValue(this.tempFiles);
  }

  reset(fileNumber: HomeworkFile) {
    const index: number = this.tempFiles.indexOf(fileNumber);
    if (index !== -1) {
      this.tempFiles.splice(index, 1);
    }
    this.validateForm.controls["files"].setValue(this.tempFiles);
  }

  getHomeworkDetails(homeworkId: number) {
    this.homeworkService.getHomeworkDetails(homeworkId).subscribe((data) => {
      this.homeworkDetails = data;
      this.mapForm();
    });
  }

  transformDate(date) {
    return this.datePipe.transform(date, "yyyy-MM-dd");
  }
}
