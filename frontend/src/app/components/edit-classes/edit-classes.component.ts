import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { Classes } from "src/app/common/classes";
import { ClassesFile } from "src/app/common/classes-file";
import { ClassesService } from "src/app/services/classes.service";
import { Location } from "@angular/common";

@Component({
  selector: "app-edit-classes",
  templateUrl: "./edit-classes.component.html",
  styleUrls: ["./edit-classes.component.css"],
})
export class EditClassesComponent implements OnInit {
  validateForm!: FormGroup;
  formSubmitted = false;

  classesFile: ClassesFile;

  classes: Classes;

  tempFiles: ClassesFile[] = new Array();

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private _location: Location,
    private classesService: ClassesService
  ) {}

  ngOnInit(): void {
    this.getLessonDetails();
    this.validateForm = this.createClassesForm();
  }

  getLessonDetails() {
    let lessonId = Number(this.route.snapshot.paramMap.get("lessonId"));
    this.classesService.getClassesDetails(lessonId).subscribe((data) => {
      this.classes = data;
      this.mapForm();
    });
  }

  mapForm() {
    this.validateForm.controls["files"].setValue(this.classes.files);
    this.validateForm.controls["url"].setValue(this.classes.url);
    this.tempFiles = this.classes.files;
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      this.classes.url = this.validateForm.value.url;
      this.classes.files = this.validateForm.value.files;
      this.classesService.updateClasses(this.classes).subscribe((data) => {
        this.changePage(data);
      });
      this.validateForm.reset();
      this.tempFiles = new Array();
      window.close();
    } 
  }

  changePage(classesId: number) {
    this._location.back();
  }

  createClassesForm(): FormGroup {
    const form = this.fb.group({
      url: [null, Validators.required],
      files: [null],
    });
    return form;
  }

  get files(): any {
    return this.validateForm.get("files");
  }

  get url(): any {
    return this.validateForm.get("url");
  }

  onUpload(event) {
    let me = this;
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.classesFile = new ClassesFile();
      me.classesFile.fileName = String(event.target.files[0].name);
      me.classesFile.fileContent = String(reader.result);
      me.tempFiles.push(me.classesFile);
    };
    reader.onerror = function (error) {
      console.log("Error: ", error);
    };

    this.validateForm.controls["files"].setValue(this.tempFiles);
  }

  reset(fileNumber: ClassesFile) {
    const index: number = this.tempFiles.indexOf(fileNumber);
    if (index !== -1) {
      this.tempFiles.splice(index, 1);
    }
    this.validateForm.controls["files"].setValue(this.tempFiles);
  }
}
