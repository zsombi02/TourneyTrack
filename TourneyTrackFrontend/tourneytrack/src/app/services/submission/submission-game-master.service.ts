import { Injectable } from '@angular/core';
import {BaseService} from '../base/base.service';
import {HttpClient} from '@angular/common/http';
import {ApproveSubmissionRequest} from '../../shared/models/requests/approve-submission-request';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubmissionGameMasterService extends BaseService {
  constructor(private http: HttpClient) { super(); }
  approveSubmission(id: number, request: ApproveSubmissionRequest): Observable<void> {
    return this.http.post<void>(`${this.API_ROOT}/api/master/submissions/${id}/approve`, request);
  }
}
