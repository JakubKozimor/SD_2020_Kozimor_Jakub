import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SchoolServiceService } from 'src/app/services/school-service.service';

@Component({
  selector: 'app-add-school',
  templateUrl: './add-school.component.html',
  styleUrls: ['./add-school.component.css']
})
export class AddSchoolComponent implements OnInit {

  validateForm!: FormGroup;
  formSubmitted = false;

  constructor(
    private fb: FormBuilder,
    private schoolService: SchoolServiceService
  ) { }

  ngOnInit(): void {
    this.validateForm = this.createSchoolForm();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      this.schoolService.addSchool(this.validateForm.value);
      this.validateForm.reset();
    } else {
      this.formSubmitted = false;
    }
  }

  createSchoolForm(): FormGroup {
    const form = this.fb.group({
      name: [null, [Validators.required]],
      city: [null, [Validators.required]],
    });
    return form;
  }
  get name(): any {
    return this.validateForm.get('name');
  }

  get city(): any {
    return this.validateForm.get('city');
  }

}
