import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RateHomeworkAnswerComponent } from './rate-homework-answer.component';

describe('RateHomeworkAnswerComponent', () => {
  let component: RateHomeworkAnswerComponent;
  let fixture: ComponentFixture<RateHomeworkAnswerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RateHomeworkAnswerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RateHomeworkAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
