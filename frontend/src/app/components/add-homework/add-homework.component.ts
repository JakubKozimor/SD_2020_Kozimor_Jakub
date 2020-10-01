import { DatePipe } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { HomeworkFile } from 'src/app/common/homework-file';
import { HomeworkService } from 'src/app/services/homework.service';

@Component({
  selector: 'app-add-homework',
  templateUrl: './add-homework.component.html',
  styleUrls: ['./add-homework.component.css']
})
export class AddHomeworkComponent implements OnInit {
  model;

  validateForm!: FormGroup;
  formSubmitted = false;

  homeworkFile: HomeworkFile;

  tempFiles: HomeworkFile[] = new Array;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private homeworkService: HomeworkService
  ) { }

  ngOnInit(): void {
    this.validateForm = this.createHomeworkForm();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      let subjectId = Number(this.route.snapshot.paramMap.get("subjectId"));
      this.validateForm.controls['deadline'].setValue(this.parseDate(this.deadline.value));
      this.homeworkService.createHomework(this.validateForm.value, subjectId);
      this.validateForm.reset();
      this.tempFiles = new Array;
    } else {
      this.formSubmitted = false;
    }
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
      files: [null]
    });
    return form;
  }

  get title(): any {
    return this.validateForm.get('title');
  }

  get description(): any {
    return this.validateForm.get('description');
  }
  get files(): any {
    return this.validateForm.get('files');
  }
  get status(): any {
    return this.validateForm.get('status');
  }
  get deadline(): any {
    return this.validateForm.get('deadline');
  }

  onUpload(event) {
    let me = this;
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.homeworkFile = new HomeworkFile;
      me.homeworkFile.fileName = String(event.target.files[0].name);
      me.homeworkFile.fileContent = String(reader.result);
      me.tempFiles.push(me.homeworkFile);
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
    };

    this.validateForm.controls['files'].setValue(this.tempFiles);
  }

  reset(fileNumber: HomeworkFile) {
    const index: number = this.tempFiles.indexOf(fileNumber);
    if (index !== -1) {
      this.tempFiles.splice(index, 1);
    }
    this.validateForm.controls['files'].setValue(this.tempFiles);
  }

}
