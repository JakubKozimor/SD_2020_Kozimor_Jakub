import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddHomeworkAnswerComponent } from './add-homework-answer.component';

describe('AddHomeworkAnswerComponent', () => {
  let component: AddHomeworkAnswerComponent;
  let fixture: ComponentFixture<AddHomeworkAnswerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddHomeworkAnswerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddHomeworkAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
