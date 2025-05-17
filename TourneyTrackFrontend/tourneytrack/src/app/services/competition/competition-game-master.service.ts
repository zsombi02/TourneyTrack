import { Injectable } from '@angular/core';
import {BaseService} from '../base/base.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UpdateCompetitionRequest } from '../../shared/models/requests/update-competition-request';
import {ScoreEntryDto} from '../../shared/models/score-entry.dto';
import {SubmissionDto} from '../../shared/models/submission.dto';
import {CreateCompetitionRequest} from '../../shared/models/requests/create-competition-request';
import {CompetitionDto} from '../../shared/models/competition.dto';

@Injectable({
  providedIn: 'root'
})
export class CompetitionGameMasterService extends BaseService {
  constructor(private http: HttpClient) { super(); }
  updateCompetition({id, request}: { id: number, request: UpdateCompetitionRequest }): Observable<void> {
    return this.http.put<void>(`${this.API_ROOT}/api/master/competitions/${id}`, request);
  }
  stopCompetition(id: number): Observable<void> {
    return this.http.patch<void>(`${this.API_ROOT}/api/master/competitions/${id}/stop`, {});
  }

  getScoreBoard(id: number): Observable<ScoreEntryDto[]> {
    return this.http.get<ScoreEntryDto[]>(`${this.API_ROOT}/api/master/competitions/${id}/scoreboard`);
  }
  getSubmissions(id: number): Observable<SubmissionDto[]> {
    return this.http.get<SubmissionDto[]>(`${this.API_ROOT}/api/master/competitions/${id}/submissions`);
  }
  deleteCompetition(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_ROOT}/api/master/competitions/${id}`);
  }
  createCompetition(request: CreateCompetitionRequest): Observable<CompetitionDto> {
    return this.http.post<CompetitionDto>(`${this.API_ROOT}/api/master/competitions/`, request);
  }
}
