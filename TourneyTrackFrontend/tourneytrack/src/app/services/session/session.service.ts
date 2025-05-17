import { Injectable } from '@angular/core';
import { UserDto } from '../../shared/models/user.dto';

@Injectable({ providedIn: 'root' })
export class SessionService {

  getCurrentUser(): UserDto | null {
    const data = localStorage.getItem('user');
    return data ? (JSON.parse(data) as UserDto) : null;
  }

  isLoggedIn(): boolean {
    return !!this.getCurrentUser();
  }

  getEmail(): string {
    return this.getCurrentUser()?.email || '';
  }

  getName(): string {
    return this.getCurrentUser()?.name || '';
  }

  getUserType(): string {
    return this.getCurrentUser()?.type || '';
  }

  getUserId(): number {
    return this.getCurrentUser()?.id || 0;
  }

  saveUser(user: UserDto, token: string): void {
    localStorage.setItem('user', JSON.stringify(user));
    localStorage.setItem('userToken', token);
  }

  clear(): void {
    localStorage.removeItem('user');
    localStorage.removeItem('userToken');
  }
}
