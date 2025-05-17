import { TestBed } from '@angular/core/testing';

import { RuleSetGameMasterService } from './rule-set-game-master.service';

describe('RuleSetGameMasterService', () => {
  let service: RuleSetGameMasterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RuleSetGameMasterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
