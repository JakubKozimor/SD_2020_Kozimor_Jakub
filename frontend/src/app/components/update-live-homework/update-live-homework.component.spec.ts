import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateLiveHomeworkComponent } from './update-live-homework.component';

describe('UpdateLiveHomeworkComponent', () => {
  let component: UpdateLiveHomeworkComponent;
  let fixture: ComponentFixture<UpdateLiveHomeworkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateLiveHomeworkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateLiveHomeworkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
