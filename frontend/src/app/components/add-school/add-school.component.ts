import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { SchoolServiceService } from "src/app/services/school-service.service";

@Component({
  selector: "app-add-school",
  templateUrl: "./add-school.component.html",
  styleUrls: ["./add-school.component.css"],
})
export class AddSchoolComponent implements OnInit {
  validateForm!: FormGroup;
  formSubmitted = false;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private schoolService: SchoolServiceService
  ) {}

  ngOnInit(): void {
    this.validateForm = this.createSchoolForm();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      this.schoolService.addSchool(this.validateForm.value);
      this.validateForm.reset();
      this.formSubmitted = false;
      this.changePage();
    }
  }

  changePage() {
    this.router.navigate(["school/all"]);
  }

  createSchoolForm(): FormGroup {
    const form = this.fb.group({
      name: [null, [Validators.required]],
      city: [null, [Validators.required]],
    });
    return form;
  }
  get name(): any {
    return this.validateForm.get("name");
  }

  get city(): any {
    return this.validateForm.get("city");
  }
}
