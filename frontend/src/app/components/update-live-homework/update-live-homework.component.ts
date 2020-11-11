import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { LiveHomework } from "src/app/common/live-homework";
import { LiveHomeworkFile } from "src/app/common/live-homework-file";
import { LiveHomeworksDetails } from "src/app/common/live-homeworks-details";
import { LiveHomeworkService } from "src/app/services/live-homework.service";

@Component({
  selector: "app-update-live-homework",
  templateUrl: "./update-live-homework.component.html",
  styleUrls: ["./update-live-homework.component.css"],
})
export class UpdateLiveHomeworkComponent implements OnInit {
  liveHomeworkDetails: LiveHomeworksDetails;

  finalEditeLiveHomework: LiveHomework;

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
    this.getLiveHomeworkDetails();
    this.validateForm = this.createHomeworkForm();
  }

  getLiveHomeworkDetails() {
    let liveHomeworkId = Number(
      this.route.snapshot.paramMap.get("liveHomeworkId")
    );
    this.liveHomeworkService
      .getHomeworkDetails(liveHomeworkId)
      .subscribe((data) => {
        this.liveHomeworkDetails = data;
        this.mapForm();
        console.log(data);
      });
  }

  mapForm() {
    this.validateForm.controls["files"].setValue(
      this.liveHomeworkDetails.files
    );
    this.validateForm.controls["description"].setValue(
      this.liveHomeworkDetails.description
    );
    this.tempFiles = this.liveHomeworkDetails.files;
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      let liveHomeworkId = Number(
        this.route.snapshot.paramMap.get("liveHomeworkId")
      );
      console.log(this.validateForm.value);
      this.liveHomeworkService.updateLiveHomework(
        this.validateForm.value,
        liveHomeworkId
      );
      this.validateForm.reset();
      this.tempFiles = new Array();
    } else {
      this.formSubmitted = false;
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
