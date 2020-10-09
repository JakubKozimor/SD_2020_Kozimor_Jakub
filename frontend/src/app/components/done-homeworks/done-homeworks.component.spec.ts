import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoneHomeworksComponent } from './done-homeworks.component';

describe('DoneHomeworksComponent', () => {
  let component: DoneHomeworksComponent;
  let fixture: ComponentFixture<DoneHomeworksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoneHomeworksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoneHomeworksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
