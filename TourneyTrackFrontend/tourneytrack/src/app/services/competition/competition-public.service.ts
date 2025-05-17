import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseService } from '../base/base.service';
import {CompetitionDto} from '../../shared/models/competition.dto';
import {ScoreEntryDto} from '../../shared/models/score-entry.dto';
import {SubmissionDto} from '../../shared/models/submission.dto';

@Injectable({ providedIn: 'root' })
export class CompetitionPublicService extends BaseService {
  constructor(private http: HttpClient) { super(); }

  getAllCompetitions(): Observable<CompetitionDto[]> {
    return this.http.get<CompetitionDto[]>(`${this.API_ROOT}/api/public/competitions`);
  }

  getCompetitionById(id: number): Observable<CompetitionDto> {
    return this.http.get<CompetitionDto>(`${this.API_ROOT}/api/public/competitions/${id}`);
  }

  getScoreBoard(id: number): Observable<ScoreEntryDto[]> {
    return this.http.get<ScoreEntryDto[]>(`${this.API_ROOT}/api/public/competitions/${id}/scoreboard`);
  }

  getSubmissions(id: number): Observable<SubmissionDto[]> {
    return this.http.get<SubmissionDto[]>(`${this.API_ROOT}/api/public/competitions/${id}/submissions`);
  }
}
