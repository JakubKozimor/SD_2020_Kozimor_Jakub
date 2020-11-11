import { TestBed } from '@angular/core/testing';

import { LiveHomeworkService } from './live-homework.service';

describe('LiveHomeworkService', () => {
  let service: LiveHomeworkService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LiveHomeworkService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
