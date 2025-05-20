import { Routes } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import {CompetitionListComponent} from './pages/competition-list/competition-list.component';
import {CompetitionDetailsComponent} from './pages/competition-details/competition-details.component';
import {RuleSetListComponent} from './pages/rule-set-list/rule-set-list.component';
import {CreateCompetitionComponent} from './pages/create-competition/create-competition.component';
import {CompetitionAdminDetailsComponent} from './pages/competition-admin-details/competition-admin-details.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'competitions', component: CompetitionListComponent },
  { path: 'competitions/:id', component: CompetitionDetailsComponent },
  { path: 'rulesets', component: RuleSetListComponent },
  { path: 'create-competition', component: CreateCompetitionComponent },
  {path: 'competitions/:id/admin', component: CompetitionAdminDetailsComponent}
];
