import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { Subject } from "src/app/common/subject";
import { SubjectFile } from "src/app/common/subject-file";
import { SubjectService } from "src/app/services/subject.service";

@Component({
  selector: "app-update-subject",
  templateUrl: "./update-subject.component.html",
  styleUrls: ["./update-subject.component.css"],
})
export class UpdateSubjectComponent implements OnInit {
  subject: Subject;

  subjectId: number;

  validateForm!: FormGroup;
  formSubmitted = false;

  subjectFile: SubjectFile;
  tempFiles: SubjectFile[] = new Array();

  constructor(
    private route: ActivatedRoute,
    private subjectService: SubjectService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getSubjectDetails();
    this.validateForm = this.createSubjectForm();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      let subjectId = this.route.snapshot.paramMap.get("subjectId");
      this.subjectService.updateSubject(
        Number(subjectId),
        this.validateForm.value
      );
      this.validateForm.reset();
      this.tempFiles = new Array();
      this.changePage();
    } else {
      this.formSubmitted = false;
    }
  }

  changePage() {
    this.router.navigate(["allSubjects"]);
  }

  mapForm() {
    this.validateForm.controls["files"].setValue(this.subject.files);
    this.validateForm.controls["name"].setValue(this.subject.name);
    this.validateForm.controls["day"].setValue(this.subject.day);
    this.validateForm.controls["week"].setValue(this.subject.week);
    this.validateForm.controls["startTime"].setValue(this.subject.startTime);
    this.validateForm.controls["longOfTime"].setValue(this.subject.longOfTime);
    this.tempFiles = this.subject.files;
  }

  createSubjectForm(): FormGroup {
    const form = this.fb.group({
      name: [null, [Validators.required]],
      day: [null, [Validators.required]],
      week: [null, [Validators.required]],
      startTime: [null, [Validators.required]],
      longOfTime: [null, [Validators.required]],
      files: [null],
    });
    return form;
  }

  get name(): any {
    return this.validateForm.get("name");
  }
  get day(): any {
    return this.validateForm.get("day");
  }
  get week(): any {
    return this.validateForm.get("week");
  }
  get startTime(): any {
    return this.validateForm.get("startTime");
  }
  get longOfTime(): any {
    return this.validateForm.get("longOfTime");
  }

  onUpload(event) {
    let me = this;
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.subjectFile = new SubjectFile();
      me.subjectFile.fileName = String(event.target.files[0].name);
      me.subjectFile.fileContent = String(reader.result);
      me.tempFiles.push(me.subjectFile);
    };
    reader.onerror = function (error) {
      console.log("Error: ", error);
    };

    this.validateForm.controls["files"].setValue(this.tempFiles);
  }

  reset(fileNumber: SubjectFile) {
    const index: number = this.tempFiles.indexOf(fileNumber);
    if (index !== -1) {
      this.tempFiles.splice(index, 1);
    }
    this.validateForm.controls["files"].setValue(this.tempFiles);
  }

  getSubjectDetails() {
    let subjectId = this.route.snapshot.paramMap.get("subjectId");
    this.subjectService
      .getSubjectDetails(Number(subjectId))
      .subscribe((data) => {
        this.subject = data;
        this.subjectId = this.subject.subjectId;
        this.mapForm();
      });
  }
}
