import { Component, OnInit } from '@angular/core';
import { CompetitionDto } from '../../shared/models/competition.dto';
import { ScoreEntryDto } from '../../shared/models/score-entry.dto';
import { SubmissionDto } from '../../shared/models/submission.dto';
import { ActivatedRoute } from '@angular/router';
import { CompetitionPublicService } from '../../services/competition/competition-public.service';
import { SessionService } from '../../services/session/session.service';
import { UserDto } from '../../shared/models/user.dto';
import { MatDialog } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import {CompetitionPlayerService} from '../../services/competition/competition-player.service';
import {SubmitScoreDialogComponent} from '../../shared/submit-score-dialog/submit-score-dialog.component';
import {SubmitScoreRequest} from '../../shared/models/requests/submit-score-request';
import {CompetitionAdminDetailsComponent} from '../competition-admin-details/competition-admin-details.component';
import {ScoreboardPlayer} from '../../shared/models/scoreboard-player';
import {RuleDto} from '../../shared/models/rule.dto';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-competition-details',
  imports: [CommonModule, MatTabsModule, MatCardModule, MatButtonModule, CompetitionAdminDetailsComponent, MatIcon],
  templateUrl: './competition-details.component.html',
  styleUrl: './competition-details.component.css'
})
export class CompetitionDetailsComponent implements OnInit {
  competition?: CompetitionDto;
  scoreboard: ScoreEntryDto[] = [];
  submissions: SubmissionDto[] = [];
  loading = true;

  selectedTab = 0;
  adminMode = false;

  playerScores: ScoreboardPlayer[] = [];
  submissionStatusFilter: string = 'ALL';
  filteredSubmissions: SubmissionDto[] = [];

  constructor(
    private route: ActivatedRoute,
    private competitionService: CompetitionPublicService,
    protected session: SessionService,
    protected competitionPlayerService: CompetitionPlayerService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.loadCompetition(id);
  }

  loadCompetition(id: number) {
    this.loading = true;
    this.competitionService.getCompetitionById(id).subscribe(data => {
      this.competition = data;
      this.processScoreboard();
    });
    this.competitionService.getScoreBoard(id).subscribe(data => {
      this.scoreboard = data;
      this.processScoreboard();
    });
    this.competitionService.getSubmissions(id).subscribe(data => {
      this.submissions = data;
      this.filterSubmissions();
    });    this.loading = false;
  }

  filterSubmissions() {
    if (this.submissionStatusFilter === 'ALL') {
      this.filteredSubmissions = this.submissions;
    } else {
      this.filteredSubmissions = this.submissions.filter(s => s.status === this.submissionStatusFilter);
    }
  }

  setSubmissionFilter(status: string) {
    this.submissionStatusFilter = status;
    this.filterSubmissions();
  }

  processScoreboard() {
    if (!this.competition) return;

    // Minden szabály id-k összegyűjtése
    const allRules: RuleDto[] = (this.competition.ruleSets || []).flatMap(rs => rs.rules);

    this.playerScores = (this.competition.participants || []).map(player => {
      // Default minden szabályhoz 0 pont
      const rulePoints: { [ruleId: number]: number } = {};
      for (const rule of allRules) rulePoints[rule.id] = 0;

      // ScoreEntryDto-kból összesítés
      this.scoreboard
        .filter(e => e.player.id === player.id)
        .forEach(e => { rulePoints[e.rule.id] = e.pointsEarned; });

      const total = Object.values(rulePoints).reduce((sum, p) => sum + p, 0);

      return { player, total, rulePoints, expanded: false };
    });

    // Ranglista pont szerint
    this.playerScores.sort((a, b) => b.total - a.total);
  }

  /** Lenyitás toggle */
  togglePlayerExpand(ps: ScoreboardPlayer) {
    ps.expanded = !ps.expanded;
  }

  // ------ LOGIKA METÓDUSOK ------
  isLoggedIn(): boolean {
    return this.session.isLoggedIn();
  }

  isPlayer(): boolean {
    return this.session.getUserType() === 'PLAYER';
  }

  isGameMaster(): boolean {
    return this.session.getUserType() === 'GAME_MASTER';
  }

  isJoined(): boolean {
    if (!this.isLoggedIn() || !this.competition) return false;
    const userId = this.session.getCurrentUser()?.id;
    return !!this.competition.participants?.some(u => u.id === userId);
  }

  getJoinButtonLabel(): string {
    return this.isJoined() ? 'Kilépek' : 'Csatlakozom';
  }

  onJoinOrLeave() {
    if (!this.competition) return;
    const id = this.competition.id!;
    if (this.isJoined()) {
      this.competitionPlayerService.leaveCompetition(id).subscribe({
        next: () => {
          alert('Sikeresen kiléptél!');
          this.loadCompetition(id); // Frissítsd a versenyt!
        },
        error: err => alert('Kilépés sikertelen: ' + (err.error?.message ?? err.message))
      });
    } else {
      this.competitionPlayerService.joinCompetition(id).subscribe({
        next: () => {
          alert('Sikeresen csatlakoztál!');
          this.loadCompetition(id); // Frissítsd a versenyt!
        },
        error: err => alert('Csatlakozás sikertelen: ' + (err.error?.message ?? err.message))
      });
    }
  }

  openSubmissionDialog() {
    if (!this.competition) return;
    const currentUser = this.session.getCurrentUser();
    if (!currentUser) return;

    // Vedd ki csak az aktuális user nem rejected submissionjeit!
    const userSubmissions = this.submissions.filter(
      sub => sub.user.id === currentUser.id && sub.status !== 'REJECTED'
    );
    this.dialog.open(SubmitScoreDialogComponent, {
      width: '400px',
      data: {
        rules: this.competition.ruleSets?.flatMap(rs => rs.rules) || [],
        userSubmissions
      }
    }).afterClosed().subscribe(result => {
      if (result && result.ruleId && result.description) {
        this.submitScore(result.ruleId, result.description);
      }
    });
  }


  submitScore(ruleId: number, description: string) {
    if (!this.competition) return;
    const req: SubmitScoreRequest = {
      competitionId: this.competition.id!,
      ruleId,
      description
      // userId backend tölti!
    };
    this.competitionPlayerService.submitScore(this.competition.id!, req).subscribe({
      next: () => {
        alert('Beadás sikeres!');
        if (this.competition && this.competition.id) {
          this.loadCompetition(this.competition.id);
        }
      },
      error: err => alert('Beadás sikertelen: ' + (err.error?.message ?? err.message))
    });
  }

  isOwnerGameMaster(): boolean {
    return <boolean>(
      this.isGameMaster() &&
      this.competition &&
      this.competition.gameMaster?.id === this.session.getCurrentUser()?.id
    );
  }

  goToAdminView() {
    this.adminMode = true;
  }

  goToNormalView() {
    this.adminMode = false;
  }

  refreshScoreboard() {
    if (!this.competition?.id) return;
    this.competitionService.getScoreBoard(this.competition.id).subscribe(data => {
      this.scoreboard = data;
      this.processScoreboard();
    });
  }

}
