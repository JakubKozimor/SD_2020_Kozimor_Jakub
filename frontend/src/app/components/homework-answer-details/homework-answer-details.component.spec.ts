import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeworkAnswerDetailsComponent } from './homework-answer-details.component';

describe('HomeworkAnswerDetailsComponent', () => {
  let component: HomeworkAnswerDetailsComponent;
  let fixture: ComponentFixture<HomeworkAnswerDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeworkAnswerDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeworkAnswerDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
