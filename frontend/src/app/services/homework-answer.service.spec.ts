import { TestBed } from '@angular/core/testing';

import { HomeworkAnswerService } from './homework-answer.service';

describe('HomeworkAnswerService', () => {
  let service: HomeworkAnswerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HomeworkAnswerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
