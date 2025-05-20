import { Injectable } from '@angular/core';
import {BaseService} from '../base/base.service';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import {RegisterUserRequest} from '../../shared/models/requests/register-user-request';
import {UserDto} from '../../shared/models/user.dto';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends BaseService {
  constructor(private http: HttpClient) {
    super();
  }

  register(data: RegisterUserRequest): Observable<void> {
    return this.http.post<void>(`${this.API_ROOT}/api/auth/register`, data);
  }

  login(email: string, password: string): Observable<UserDto> {
    return this.http.get<UserDto>(`${this.API_ROOT}/api/auth/me`, {
      headers: {
        Authorization: 'Basic ' + btoa(email + ':' + password)
      }
    });
  }
}
