import { Component, OnInit } from '@angular/core';
import { CompetitionDto } from '../../shared/models/competition.dto';
import { SubmissionDto } from '../../shared/models/submission.dto';
import { CompetitionPlayerService } from '../../services/competition/competition-player.service';
import { CompetitionGameMasterService } from '../../services/competition/competition-game-master.service';
import { SessionService } from '../../services/session/session.service';
import { Router } from '@angular/router';
import { SubmissionPlayerService } from '../../services/submission/submission-player.service';
import {DatePipe, NgClass, NgForOf, NgIf} from '@angular/common';
import {MatCard} from '@angular/material/card';
import {MatButton} from '@angular/material/button';
import {
  MatAccordion,
  MatExpansionPanel,
  MatExpansionPanelHeader,
  MatExpansionPanelTitle
} from '@angular/material/expansion';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  standalone: true,
  imports: [
    DatePipe,
    NgClass,
    MatCard,
    MatButton,
    MatAccordion,
    MatExpansionPanel,
    MatExpansionPanelTitle,
    NgIf,
    MatExpansionPanelHeader,
    NgForOf
  ]
})
export class HomeComponent implements OnInit {
  competitions: CompetitionDto[] = [];
  submissions: SubmissionDto[] = [];
  loading = true;
  isGameMaster = false;

  constructor(
    private compPlayerService: CompetitionPlayerService,
    private compGameMasterService: CompetitionGameMasterService,
    private submissionService: SubmissionPlayerService,
    public session: SessionService,
    private router: Router
  ) {}

  ngOnInit() {
    if (!this.session.isLoggedIn()) {
      this.router.navigate(['/competitions']);
    }
    this.isGameMaster = this.session.getUserType() === 'GAME_MASTER';
    if (this.isGameMaster) {
      // Saját szervezésű versenyek
      this.compGameMasterService.getMyCompetitions().subscribe({
        next: data => {
          this.competitions = data;
          this.loading = false;
        },
        error: () => this.loading = false
      });
    } else {
      // Playerként a résztvevői versenyek
      this.compPlayerService.listMyCompetitions().subscribe({
        next: data => this.competitions = data,
        error: () => {},
        complete: () => this.loading = false
      });
      // Saját beadások
      this.submissionService.getMySubmissions().subscribe({
        next: data => this.submissions = data,
        error: () => {}
      });
    }
  }

  goToAdminDetails(id: number) {
    this.router.navigate(['/competitions', id, 'admin']);
  }

  goToDetails(id: number) {
    this.router.navigate(['/competitions', id]);
  }

  getMySubmissionsForCompetition(compId: number): SubmissionDto[] {
    return this.submissions.filter(sub => sub.competitionId === compId);
  }
}
