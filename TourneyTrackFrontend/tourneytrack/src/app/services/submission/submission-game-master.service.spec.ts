import { TestBed } from '@angular/core/testing';

import { SubmissionGameMasterService } from './submission-game-master.service';

describe('SubmissionGameMasterService', () => {
  let service: SubmissionGameMasterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SubmissionGameMasterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
