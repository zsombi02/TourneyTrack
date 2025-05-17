import { TestBed } from '@angular/core/testing';

import { SubmissionPlayerService } from './submission-player.service';

describe('SubmissionPlayerService', () => {
  let service: SubmissionPlayerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SubmissionPlayerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
