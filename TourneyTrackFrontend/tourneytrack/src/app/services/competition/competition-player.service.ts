import { Injectable } from '@angular/core';
import {BaseService} from '../base/base.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CompetitionDto} from '../../shared/models/competition.dto';
import {SubmitScoreRequest} from '../../shared/models/requests/submit-score-request';

@Injectable({
  providedIn: 'root'
})
export class CompetitionPlayerService extends BaseService {
  constructor(private http: HttpClient) { super(); }

  // Csatlakozás
  joinCompetition(competitionId: number): Observable<void> {
    return this.http.post<void>(`${this.API_ROOT}/api/player/competitions/${competitionId}/join`, {});
  }

  // Kilépés
  leaveCompetition(competitionId: number): Observable<void> {
    return this.http.post<void>(`${this.API_ROOT}/api/player/competitions/${competitionId}/leave`, {});
  }

  // Saját versenyeim
  listMyCompetitions(): Observable<CompetitionDto[]> {
    return this.http.get<CompetitionDto[]>(`${this.API_ROOT}/api/player/competitions`);
  }

  // Beadás (Submit)
  submitScore(competitionId: number, request: SubmitScoreRequest): Observable<void> {
    return this.http.post<void>(`${this.API_ROOT}/api/player/competitions/${competitionId}/submit`, request);
  }
}
