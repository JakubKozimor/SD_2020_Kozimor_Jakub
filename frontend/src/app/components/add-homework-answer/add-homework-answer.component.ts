import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { HomeworkAnswer } from 'src/app/common/homework-answer';
import { HomeworkAnswerFile } from 'src/app/common/homework-answer-file';
import { HomeworkAnswerService } from 'src/app/services/homework-answer.service';

@Component({
  selector: 'app-add-homework-answer',
  templateUrl: './add-homework-answer.component.html',
  styleUrls: ['./add-homework-answer.component.css']
})
export class AddHomeworkAnswerComponent implements OnInit {

  validateForm!: FormGroup;
  formSubmitted = false;
  homeworkFile: HomeworkAnswerFile;
  tempFiles: HomeworkAnswerFile[] = new Array;
  homeworkAnswer: HomeworkAnswer;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private homeworkAnswerService: HomeworkAnswerService) { }

    ngOnInit(): void {
      this.validateForm = this.createHomeworkAnswerForm();
    }

    submitForm() {
      this.formSubmitted = true;
      if (this.validateForm.valid) {
        let homeworkId = Number(this.route.snapshot.paramMap.get("homeworkId"));
        this.homeworkAnswer = this.validateForm.value;
        this.homeworkAnswer.homeworkId = homeworkId;
        this.homeworkAnswerService.addAnswer(this.homeworkAnswer);
        this.validateForm.reset();
        this.tempFiles = new Array;
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

    get description(): any {
      return this.validateForm.get('description');
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
}
