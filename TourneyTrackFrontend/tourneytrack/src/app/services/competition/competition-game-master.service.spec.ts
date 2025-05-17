import { TestBed } from '@angular/core/testing';

import { CompetitionGameMasterService } from './competition-game-master.service';

describe('CompetitionGameMasterService', () => {
  let service: CompetitionGameMasterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompetitionGameMasterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
