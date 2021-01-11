import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubjecthomeworkteacherComponent } from './subjecthomeworkteacher.component';

describe('SubjecthomeworkteacherComponent', () => {
  let component: SubjecthomeworkteacherComponent;
  let fixture: ComponentFixture<SubjecthomeworkteacherComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubjecthomeworkteacherComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubjecthomeworkteacherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
