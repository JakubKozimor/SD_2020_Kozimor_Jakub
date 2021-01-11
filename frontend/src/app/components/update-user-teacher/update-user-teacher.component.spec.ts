import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateUserTeacherComponent } from './update-user-teacher.component';

describe('UpdateUserTeacherComponent', () => {
  let component: UpdateUserTeacherComponent;
  let fixture: ComponentFixture<UpdateUserTeacherComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateUserTeacherComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateUserTeacherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
