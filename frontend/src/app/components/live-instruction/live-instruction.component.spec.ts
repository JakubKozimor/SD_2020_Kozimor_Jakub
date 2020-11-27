import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LiveInstructionComponent } from './live-instruction.component';

describe('LiveInstructionComponent', () => {
  let component: LiveInstructionComponent;
  let fixture: ComponentFixture<LiveInstructionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LiveInstructionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LiveInstructionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
