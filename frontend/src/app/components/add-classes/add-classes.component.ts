import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Classes } from 'src/app/common/classes';
import { ClassesFile } from 'src/app/common/classes-file';
import { ClassesService } from 'src/app/services/classes.service';

@Component({
  selector: 'app-add-classes',
  templateUrl: './add-classes.component.html',
  styleUrls: ['./add-classes.component.css']
})
export class AddClassesComponent implements OnInit {
  validateForm!: FormGroup;
  formSubmitted = false;

  classesFile: ClassesFile;

  showInstruction = false;
  firstTime = false;

  classes: Classes;

  tempFiles: ClassesFile[] = new Array;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private router:Router,
    private classesService: ClassesService
  ) { }

  ngOnInit(): void {
    this.validateForm = this.createClassesForm();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      let subjectId = Number(this.route.snapshot.paramMap.get("subjectId"));
      let classes = new Classes();
      classes = this.validateForm.value;
      classes.subjectId = subjectId; 
      this.classesService.createClasses(this.validateForm.value).subscribe(data => {
        this.changePage(data);
      });
      this.validateForm.reset();
      this.tempFiles = new Array;
    } else {
      this.formSubmitted = false;
    }
    
  }

  changePage(classesId: number){
    this.router.navigate(['classes/'+classesId]);
  }

  createClassesForm(): FormGroup {
    const form = this.fb.group({
      files: [null]
    });
    return form;
  }

  get files(): any {
    return this.validateForm.get('files');
  }

  onUpload(event) {
    let me = this;
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.classesFile = new ClassesFile;
      me.classesFile.fileName = String(event.target.files[0].name);
      me.classesFile.fileContent = String(reader.result);
      me.tempFiles.push(me.classesFile);
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
    };

    this.validateForm.controls['files'].setValue(this.tempFiles);
  }

  reset(fileNumber: ClassesFile) {
    const index: number = this.tempFiles.indexOf(fileNumber);
    if (index !== -1) {
      this.tempFiles.splice(index, 1);
    }
    this.validateForm.controls['files'].setValue(this.tempFiles);
  }

  checkShowInstuction(){
    if(this.showInstruction){
      this.showInstruction = false;
    } else {
      this.showInstruction = true;
    }
  }

  checkFirstTime(){
    if(this.firstTime){
      this.firstTime = false;
    } else {
      this.firstTime = true;
    }
  }
}
