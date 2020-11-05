import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { School } from "src/app/common/school";
import { SchoolServiceService } from "src/app/services/school-service.service";

@Component({
  selector: "app-update-school",
  templateUrl: "./update-school.component.html",
  styleUrls: ["./update-school.component.css"],
})
export class UpdateSchoolComponent implements OnInit {
  school: School;

  validateForm!: FormGroup;
  formSubmitted = false;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private schoolService: SchoolServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    let schoolId = this.route.snapshot.paramMap.get("schoolId");
    this.getSchoolDetails(Number(schoolId));
    this.validateForm = this.createSchoolForm();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      this.schoolService.updateSchool(
        this.validateForm.value,
        this.school.schoolId
      );
      this.validateForm.reset();
    } else {
      this.formSubmitted = false;
    }
  }

  mapForm() {
    this.validateForm.controls["name"].setValue(this.school.name);
    this.validateForm.controls["city"].setValue(this.school.city);
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

  getSchoolDetails(schoolId: number) {
    this.schoolService.getSchoolDetails(schoolId).subscribe((data) => {
      this.school = data;
      this.mapForm();
    });
  }
}
