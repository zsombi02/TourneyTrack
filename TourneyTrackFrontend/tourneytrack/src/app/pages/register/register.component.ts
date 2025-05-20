import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {AuthService} from '../../services/auth/auth.service';
import {MatOption} from '@angular/material/core';
import {MatSelect} from '@angular/material/select';
import {UserTypeDto} from '../../shared/models/user-type.enum';
import {RegisterUserRequest} from '../../shared/models/requests/register-user-request';
import {RouterLink} from '@angular/router';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-register',
  imports: [CommonModule, FormsModule, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatOption, MatSelect, RouterLink, MatIcon],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  name = '';
  email = '';
  password = '';
  type: UserTypeDto | '' = '';

  userTypes = [
    { value: UserTypeDto.PLAYER, label: 'Versenyző' },
    { value: UserTypeDto.GAME_MASTER, label: 'Játékmester' }
  ];

  constructor(private auth: AuthService) {}

  onSubmit() {
    if (!this.name || !this.email || !this.password || !this.type) {
      return;
    }
    const req: RegisterUserRequest = {
      name: this.name,
      email: this.email,
      password: this.password,
      type: this.type as UserTypeDto
    };
    this.auth.register(req).subscribe({
      next: () => alert('Sikeres regisztráció!'),
      error: err => alert(err.message)
    });
  }
}
