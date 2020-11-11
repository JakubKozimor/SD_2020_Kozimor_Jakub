import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLiveHomeworkComponent } from './add-live-homework.component';

describe('AddLiveHomeworkComponent', () => {
  let component: AddLiveHomeworkComponent;
  let fixture: ComponentFixture<AddLiveHomeworkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddLiveHomeworkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddLiveHomeworkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
