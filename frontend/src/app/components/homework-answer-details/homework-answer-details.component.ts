import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { HomeworkAnswer } from 'src/app/common/homework-answer';
import { HomeworkAnswerDetails } from 'src/app/common/homework-answer-details';
import { HomeworkAnswerFile } from 'src/app/common/homework-answer-file';
import { HomeworkDetails } from 'src/app/common/homework-details';
import { FileServiceService } from 'src/app/services/file-service.service';
import { HomeworkAnswerService } from 'src/app/services/homework-answer.service';
import { HomeworkService } from 'src/app/services/homework.service';

@Component({
  selector: 'app-homework-answer-details',
  templateUrl: './homework-answer-details.component.html',
  styleUrls: ['./homework-answer-details.component.css']
})
export class HomeworkAnswerDetailsComponent implements OnInit {

  homeworkAnswerDetails: HomeworkAnswerDetails;
  homeworkDetails: HomeworkDetails;

  validateForm!: FormGroup;
  formSubmitted = false;
  homeworkFile: HomeworkAnswerFile;
  tempFiles: HomeworkAnswerFile[] = new Array;
  homeworkAnswer: HomeworkAnswer;

  editAnswer = false;

  constructor(
    private homeworkService: HomeworkService,
    private route: ActivatedRoute,
    private datePipe: DatePipe,
    private fileService: FileServiceService,
    private homeworkAnswerService: HomeworkAnswerService,
    private fb: FormBuilder,
  ) { }

  ngOnInit(): void {
    let homeworkId = this.route.snapshot.paramMap.get('homeworkId');
    this.getHomeworkAnswerDetails(Number(homeworkId));
    this.getHomeworkDetails(Number(homeworkId));
    this.validateForm = this.createHomeworkAnswerForm();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      let homeworkId = Number(this.route.snapshot.paramMap.get("homeworkId"));
      this.homeworkAnswer = this.validateForm.value;
      this.homeworkAnswer.homeworkId = homeworkId;
      this.homeworkAnswer.homeworkAnswerId = this.homeworkAnswerDetails.homeworkAnswerId;
      console.log(this.homeworkAnswer);
      this.homeworkAnswerService.updateAnswer(this.homeworkAnswer);
      this.editAnswer = false;
      this.validateForm.reset();
      this.tempFiles = new Array;
      location.reload();
    } else {
      this.formSubmitted = false;
    }
  }

  createHomeworkAnswerForm(): FormGroup {
    const form = this.fb.group({
      message: [null],
      files: [null]
    });
    return form;
  }


  onUpload(event) {
    let me = this;
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.homeworkFile = new HomeworkAnswerFile;
      me.homeworkFile.fileName = String(event.target.files[0].name);
      me.homeworkFile.fileContent = String(reader.result);
      me.tempFiles.push(me.homeworkFile);
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
    };

    this.validateForm.controls['files'].setValue(this.tempFiles);
  }

  reset(fileNumber: HomeworkAnswerFile) {
    const index: number = this.tempFiles.indexOf(fileNumber);
    if (index !== -1) {
      this.tempFiles.splice(index, 1);
    }
    this.validateForm.controls['files'].setValue(this.tempFiles);
  }

  mapForm(){
    this.editAnswer = true;
    this.validateForm.controls['files'].setValue(this.homeworkAnswerDetails.files);
    this.validateForm.controls['message'].setValue(this.homeworkAnswerDetails.message);
    this.tempFiles = this.homeworkAnswerDetails.files;
  }
  

  getHomeworkAnswerDetails(homeworkId: number) {
    this.homeworkAnswerService.getHomeworkDetails(homeworkId).subscribe(data => this.homeworkAnswerDetails = data);
  }

  getHomeworkDetails(homeworkId: number) {
    this.homeworkService.getHomeworkDetails(homeworkId).subscribe(data => this.homeworkDetails = data);
  }

  downloadHomeworkAnswerFile(fileId: number, fileName: string) {
    this.fileService.downloadHomeworkAnswerFile(fileId, fileName);
  }

  downloadHomeworkFile(fileId: number, fileName: string) {
    this.fileService.downloadHomeworkFile(fileId, fileName);
  }

  transformDate(date) {
    return this.datePipe.transform(date, 'yyyy-MM-dd');
  }

  isBeforeDeadline(deadline: Date): boolean {
    let today = new Date;
    today.setDate(today.getDate() - 1)
    let deadlineInDate = new Date(deadline);
    return today < deadlineInDate;
  }
}
