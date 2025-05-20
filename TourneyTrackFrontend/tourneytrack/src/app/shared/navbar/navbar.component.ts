import { Component } from '@angular/core';
import {MatButton, MatButtonModule} from '@angular/material/button';
import {MatFormField, MatInput, MatInputModule} from '@angular/material/input';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatToolbar} from '@angular/material/toolbar';
import {Router, RouterLink} from '@angular/router';
import {AuthService} from '../../services/auth/auth.service';
import {UserDto} from '../models/user.dto';
import {SessionService} from '../../services/session/session.service';
import {MatIcon} from '@angular/material/icon';


@Component({
  selector: 'app-navbar',
  imports: [CommonModule, MatToolbar, MatIcon, MatFormField, MatInput, MatButton, FormsModule,],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  loginEmail: string = '';
  loginPassword: string = '';
  errorMsg: string = '';

  constructor(private router: Router, private auth: AuthService, protected session: SessionService) {}

  onLogin(): void {
    this.errorMsg = '';
    this.auth.login(this.loginEmail, this.loginPassword).subscribe({
      next: (user: UserDto) => {
        this.session.saveUser(user, btoa(this.loginEmail + ':' + this.loginPassword));
        this.loginEmail = '';
        this.loginPassword = '';
        // Itt pl. átirányíthatsz, vagy reload, stb.
      },
      error: err => {
        this.errorMsg = 'Hibás email vagy jelszó!';
      }
    });
  }

  logout(): void {
    this.session.clear()
    this.router.navigate(['/']);
  }

  goToRegister(): void {
    this.router.navigate(['/register']);
  }

  goToHome() {
    this.router.navigate(['/']);
  }

  goToCompetitions() {
    this.router.navigate(['/competitions']);
  }

  goToMyCompetitions() {
    this.router.navigate(['/']);
  }

  goToProfile() {
    this.router.navigate(['/profile']);
  }

  goToRulesets() {
    this.router.navigate(['/rulesets']);
  }

  goToCreateCompetition() {
    this.router.navigate(['/create-competition']);
  }
}
