import { Injectable } from '@angular/core';
import { BaseService } from '../base/base.service';
import { HttpClient } from '@angular/common/http';
import { RuleSetDto } from '../../shared/models/rule-set.dto';
import { Observable } from 'rxjs';
import {CreateRuleSetRequest} from '../../shared/models/requests/create-rule-set-request';
import {CreateRuleRequest} from '../../shared/models/requests/create-rule-request';
import {UpdateRuleRequest} from '../../shared/models/requests/update-rule-request';

@Injectable({
  providedIn: 'root'
})
export class RuleSetGameMasterService extends BaseService {
  constructor(private http: HttpClient) {
    super();
  }

  listAll(): Observable<RuleSetDto[]> {
    return this.http.get<RuleSetDto[]>(`${this.API_ROOT}/api/master/rulesets`);
  }

  createRuleSet(request: CreateRuleSetRequest): Observable<RuleSetDto> {
    return this.http.post<RuleSetDto>(`${this.API_ROOT}/api/master/rulesets`, request);
  }

  updateRuleSet(id: number, request: CreateRuleSetRequest): Observable<RuleSetDto> {
    return this.http.put<RuleSetDto>(`${this.API_ROOT}/api/master/rulesets/${id}`, request);
  }

  deleteRuleSet(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_ROOT}/api/master/rulesets/${id}`);
  }

  addRuleToRuleSet(rulesetId: number, request: CreateRuleRequest): Observable<RuleSetDto> {
    return this.http.post<RuleSetDto>(`${this.API_ROOT}/api/master/rulesets/${rulesetId}/rules`, request);
  }

  updateRuleInRuleSet(rulesetId: number, ruleId: number, request: UpdateRuleRequest): Observable<RuleSetDto> {
    return this.http.put<RuleSetDto>(`${this.API_ROOT}/api/master/rulesets/${rulesetId}/rules/${ruleId}`, request);
  }

  deleteRuleFromRuleSet(rulesetId: number, ruleId: number): Observable<RuleSetDto> {
    return this.http.delete<RuleSetDto>(`${this.API_ROOT}/api/master/rulesets/${rulesetId}/rules/${ruleId}`);
  }
}
