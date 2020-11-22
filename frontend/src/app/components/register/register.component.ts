import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { School } from "src/app/common/school";
import { AuthServiceService } from "src/app/services/auth-service.service";
import { SchoolServiceService } from "src/app/services/school-service.service";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
})
export class RegisterComponent implements OnInit {
  validateForm!: FormGroup;
  formSubmitted = false;
  theSamePassword = false;
  message: string;
  failedMessage: string;

  warningForCheckbox = false;

  teacherForm = false;

  twitchNickEmpty = true;

  schoolList: School[];

  constructor(
    private fb: FormBuilder,
    private authService: AuthServiceService,
    private schoolService: SchoolServiceService
  ) {}

  ngOnInit(): void {
    this.validateForm = this.createRegisterForm();
    this.listOfSchools();
  }

  listOfSchools() {
    this.schoolService.getAllSchoolsForSelect().subscribe((data) => {
      this.schoolList = data;
    });
  }

  submitForm() {
    let element = <HTMLInputElement>document.getElementById("checkbox");
    this.failedMessage = undefined;
    this.message = undefined;
    this.formSubmitted = true;
    if (
      this.validateForm.valid &&
      this.theSamePassword &&
      this.warningForCheckbox
    ) {
      this.authService
        .register(this.validateForm.value)
        .then((data) => {
          this.message = data;
          this.validateForm.reset();
        })
        .catch((error) => (this.failedMessage = error));
      this.validateForm.reset();
      this.formSubmitted = false;
      let element = <HTMLInputElement>document.getElementById("checkbox");
      element.checked = false;
      this.warningForCheckbox = false;
    }
  }

  chancheRegulations() {
    let element = <HTMLInputElement>document.getElementById("checkbox");
    if (element.checked) {
      element.checked = false;
    } else {
      element.checked = true;
    }
    this.changeWarningForCheckbox();
  }

  changeWarningForCheckbox() {
    if (this.warningForCheckbox) {
      this.warningForCheckbox = false;
    } else {
      this.warningForCheckbox = true;
    }
  }

  twitchNickIsEmpty() {
    if (
      this.teacherForm &&
      (this.twitchNick.value == null || this.twitchNick.value == "")
    ) {
      this.twitchNickEmpty = true;
    } else {
      this.twitchNickEmpty = false;
    }
  }

  createRegisterForm(): FormGroup {
    const form = this.fb.group({
      firstName: [null, [Validators.required]],
      lastName: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
      passwordConfirmation: [null, [Validators.required]],
      roleName: [null, [Validators.required]],
      twitchNick: [null],
      schoolName: [null, [Validators.required]],
    });
    return form;
  }

  teacherRegisterForm() {
    if (this.roleName.value == "ROLE_STUDENT") {
      this.teacherForm = false;
    }

    if (this.roleName.value == "ROLE_TEACHER") {
      this.teacherForm = true;
    }
    this.twitchNick.value = null;
  }

  checkPasswordConfirmation() {
    if (this.password.value == this.passwordConfirmation.value) {
      this.theSamePassword = true;
    } else {
      this.theSamePassword = false;
    }
  }

  get firstName(): any {
    return this.validateForm.get("firstName");
  }

  get lastName(): any {
    return this.validateForm.get("lastName");
  }
  get email(): any {
    return this.validateForm.get("email");
  }

  get password(): any {
    return this.validateForm.get("password");
  }

  get passwordConfirmation(): any {
    return this.validateForm.get("passwordConfirmation");
  }

  get roleName(): any {
    return this.validateForm.get("roleName");
  }

  get twitchNick(): any {
    return this.validateForm.get("twitchNick");
  }

  get schoolName(): any {
    return this.validateForm.get("schoolName");
  }
}
