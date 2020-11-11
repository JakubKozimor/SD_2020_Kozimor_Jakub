import { TestBed } from '@angular/core/testing';

import { LiveHomeworksAnswerService } from './live-homeworks-answer.service';

describe('LiveHomeworksAnswerService', () => {
  let service: LiveHomeworksAnswerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LiveHomeworksAnswerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
