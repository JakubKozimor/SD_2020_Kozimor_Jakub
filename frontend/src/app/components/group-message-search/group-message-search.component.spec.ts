import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupMessageSearchComponent } from './group-message-search.component';

describe('GroupMessageSearchComponent', () => {
  let component: GroupMessageSearchComponent;
  let fixture: ComponentFixture<GroupMessageSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupMessageSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupMessageSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
