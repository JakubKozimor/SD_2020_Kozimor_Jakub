import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Homework } from "src/app/common/homework";
import { LiveHomework } from "src/app/common/live-homework";
import { LiveHomeworkFile } from "src/app/common/live-homework-file";
import { LiveHomeworkService } from "src/app/services/live-homework.service";

@Component({
  selector: "app-add-live-homework",
  templateUrl: "./add-live-homework.component.html",
  styleUrls: ["./add-live-homework.component.css"],
})
export class AddLiveHomeworkComponent implements OnInit {
  liveHomeworks: LiveHomework[];
  validateForm!: FormGroup;
  formSubmitted = false;

  homeworkFile: LiveHomeworkFile;
  tempFiles: LiveHomeworkFile[] = new Array();

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private liveHomeworkService: LiveHomeworkService
  ) {}

  ngOnInit(): void {
    this.validateForm = this.createHomeworkForm();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      let classesId = Number(this.route.snapshot.paramMap.get("classesId"));
      this.liveHomeworkService.createLiveHomework(
        this.validateForm.value,
        classesId
      );

      this.tempFiles = new Array();
      setTimeout(() => {
        window.close();
      }, 500);
    }
  }

  createHomeworkForm(): FormGroup {
    const form = this.fb.group({
      description: [null, [Validators.required]],
      files: [null],
    });
    return form;
  }

  get description(): any {
    return this.validateForm.get("description");
  }

  get files(): any {
    return this.validateForm.get("files");
  }

  onUpload(event) {
    let me = this;
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.homeworkFile = new LiveHomeworkFile();
      me.homeworkFile.fileName = String(event.target.files[0].name);
      me.homeworkFile.fileContent = String(reader.result);
      me.tempFiles.push(me.homeworkFile);
    };
    reader.onerror = function (error) {
      console.log("Error: ", error);
    };

    this.validateForm.controls["files"].setValue(this.tempFiles);
  }

  reset(fileNumber: LiveHomeworkFile) {
    const index: number = this.tempFiles.indexOf(fileNumber);
    if (index !== -1) {
      this.tempFiles.splice(index, 1);
    }
    this.validateForm.controls["files"].setValue(this.tempFiles);
  }
}
