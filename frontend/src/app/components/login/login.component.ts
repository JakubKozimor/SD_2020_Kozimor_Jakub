import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AuthServiceService } from "src/app/services/auth-service.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  validateForm!: FormGroup;
  formSubmitted = false;

  wrongData = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthServiceService
  ) {}

  ngOnInit(): void {
    this.validateForm = this.createLoginForm();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid) {
      this.authService.login(this.validateForm.value).catch((err) => {
        this.wrongData = true;
      });
    }
  }

  changeData() {
    this.wrongData = false;
  }

  createLoginForm(): FormGroup {
    const form = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
    });
    return form;
  }

  get email(): any {
    return this.validateForm.get("email");
  }

  get password(): any {
    return this.validateForm.get("password");
  }
}
