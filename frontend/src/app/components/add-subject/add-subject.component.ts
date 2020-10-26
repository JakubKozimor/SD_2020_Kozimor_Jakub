import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject } from 'src/app/common/subject';
import { SubjectFile } from 'src/app/common/subject-file';
import { SubjectService } from 'src/app/services/subject.service';

@Component({
  selector: 'app-add-subject',
  templateUrl: './add-subject.component.html',
  styleUrls: ['./add-subject.component.css']
})
export class AddSubjectComponent implements OnInit {

  validateForm!: FormGroup;
  formSubmitted = false;

  subjectFile: SubjectFile;
  tempFiles: SubjectFile[] = new Array;


  constructor(
    private fb: FormBuilder,
    private router: Router,
    private subjectService: SubjectService
  ) { }

  ngOnInit(): void {
    this.validateForm = this.createSubjectForm();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      console.log(this.validateForm.value)
      this.subjectService.addMessage(this.validateForm.value).subscribe(data => {
        this.changePage(data);
      });
      this.validateForm.reset();
      this.tempFiles = new Array;
    } else {
      this.formSubmitted = false;
    }
  }

  changePage(subjectId: number) {
    this.router.navigate(['add-students/' + subjectId]);
  }

  createSubjectForm(): FormGroup {
    const form = this.fb.group({
      name: [null, [Validators.required]],
      day: [null, [Validators.required]],
      week: [null, [Validators.required]],
      startTime: [null, [Validators.required]],
      longOfTime: [null, [Validators.required]],
      files: [null]
    });
    return form;
  }

  get name(): any {
    return this.validateForm.get('name');
  }
  get day(): any {
    return this.validateForm.get('day');
  }
  get week(): any {
    return this.validateForm.get('week');
  }
  get startTime(): any {
    return this.validateForm.get('startTime');
  }
  get longOfTime(): any {
    return this.validateForm.get('longOfTime');
  }

  onUpload(event) {
    let me = this;
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.subjectFile = new SubjectFile;
      me.subjectFile.fileName = String(event.target.files[0].name);
      me.subjectFile.fileContent = String(reader.result);
      me.tempFiles.push(me.subjectFile);
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
    };

    this.validateForm.controls['files'].setValue(this.tempFiles);
  }

  reset(fileNumber: SubjectFile) {
    const index: number = this.tempFiles.indexOf(fileNumber);
    if (index !== -1) {
      this.tempFiles.splice(index, 1);
    }
    this.validateForm.controls['files'].setValue(this.tempFiles);
  }
}
