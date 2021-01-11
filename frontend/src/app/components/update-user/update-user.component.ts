import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { UpdateUser } from "src/app/common/update-user";
import { UserService } from "src/app/services/user.service";

@Component({
  selector: "app-update-user",
  templateUrl: "./update-user.component.html",
  styleUrls: ["./update-user.component.css"],
})
export class UpdateUserComponent implements OnInit {
  validateForm!: FormGroup;
  formSubmitted = false;
  theSamePassword = true;
  message: string;
  failedMessage: string;

  updateUserOld: UpdateUser;
  updateUserNew: UpdateUser;

  warningForCheckbox = false;
  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getUpdateUser();
    this.validateForm = this.createRegisterForm();
  }

  getUpdateUser() {
    this.userService.getUpdateUser().subscribe((data) => {
      this.updateUserOld = data;
      this.mapForm();
    });
  }

  mapForm() {
    this.validateForm.controls["firstName"].setValue(
      this.updateUserOld.firstName
    );
    this.validateForm.controls["lastName"].setValue(
      this.updateUserOld.lastName
    );
    this.validateForm.controls["email"].setValue(this.updateUserOld.email);
  }

  submitForm() {
    this.failedMessage = undefined;
    this.message = undefined;
    this.formSubmitted = true;
    this.updateUserNew = this.validateForm.value;
    console.log();
    if (!this.validatePassword() && !this.validateRepeatPassword()) {
      if (this.theSamePassword) {
        this.userService
          .updateUser(this.updateUserNew)
          .then((data) => {
            this.message = data;
          })
          .catch((error) => (this.failedMessage = error));
        setTimeout(() => {
          this.router.navigate(["update-user"]);
        }, 500);
      }
    } else {
      this.userService
        .updateUser(this.updateUserNew)
        .then((data) => {
          this.message = data;
        })
        .catch((error) => (this.failedMessage = error));
      setTimeout(() => {
        this.router.navigate(["update-user"]);
      }, 500);
    }
    this.formSubmitted = false;
  }

  validatePassword(): boolean {
    return (
      this.validateForm.value.password == "" ||
      this.validateForm.value.password == null
    );
  }

  validateRepeatPassword(): boolean {
    return (
      this.validateForm.value.passwordConfirmation == "" ||
      this.validateForm.value.passwordConfirmation == null
    );
  }

  createRegisterForm(): FormGroup {
    const form = this.fb.group({
      firstName: [null],
      lastName: [null],
      email: [null],
      password: [null],
      passwordConfirmation: [null],
    });
    return form;
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
}
