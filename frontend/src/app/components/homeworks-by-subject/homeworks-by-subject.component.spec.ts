import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeworksBySubjectComponent } from './homeworks-by-subject.component';

describe('HomeworksBySubjectComponent', () => {
  let component: HomeworksBySubjectComponent;
  let fixture: ComponentFixture<HomeworksBySubjectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeworksBySubjectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeworksBySubjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
