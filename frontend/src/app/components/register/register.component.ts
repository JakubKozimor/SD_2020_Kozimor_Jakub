import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthServiceService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  validateForm!: FormGroup;
  formSubmitted = false;
  theSamePassword = false;
  message: string;

  constructor(
    private fb: FormBuilder,
    private authService: AuthServiceService) { }

  ngOnInit(): void {
    this.validateForm = this.createRegisterForm();
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.validateForm.valid && this.theSamePassword) {
      this.authService.register(this.validateForm.value)
        .then(data => {
          this.message = (data);
          this.validateForm.reset();
        })
        .catch(error => this.message = (error));
      this.validateForm.reset();
    } else {
      this.formSubmitted = false;
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
    return this.validateForm.get('firstName');
  }

  get lastName(): any {
    return this.validateForm.get('lastName');
  }
  get email(): any {
    return this.validateForm.get('email');
  }

  get password(): any {
    return this.validateForm.get('password');
  }

  get passwordConfirmation(): any {
    return this.validateForm.get('passwordConfirmation');
  }

  get roleName(): any {
    return this.validateForm.get('roleName');
  }
}
