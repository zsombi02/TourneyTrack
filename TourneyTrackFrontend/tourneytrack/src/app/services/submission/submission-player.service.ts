import { Injectable } from '@angular/core';
import {BaseService} from '../base/base.service';
import {HttpClient} from '@angular/common/http';
import {SubmissionDto} from '../../shared/models/submission.dto';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubmissionPlayerService extends BaseService {
  constructor(private http: HttpClient) { super(); }

  getMySubmissions(): Observable<SubmissionDto[]> {
    return this.http.get<SubmissionDto[]>(`${this.API_ROOT}/api/player/submissions`);
  }
}
