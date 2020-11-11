import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllLiveHomeworkAnswersForTeacherComponent } from './all-live-homework-answers-for-teacher.component';

describe('AllLiveHomeworkAnswersForTeacherComponent', () => {
  let component: AllLiveHomeworkAnswersForTeacherComponent;
  let fixture: ComponentFixture<AllLiveHomeworkAnswersForTeacherComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllLiveHomeworkAnswersForTeacherComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllLiveHomeworkAnswersForTeacherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
