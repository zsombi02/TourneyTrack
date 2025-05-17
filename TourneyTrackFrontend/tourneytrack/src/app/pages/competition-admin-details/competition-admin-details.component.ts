import {Component, Input, Output, EventEmitter, OnInit, OnChanges} from '@angular/core';
import { CompetitionDto } from '../../shared/models/competition.dto';
import { ScoreEntryDto } from '../../shared/models/score-entry.dto';
import { SubmissionDto } from '../../shared/models/submission.dto';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import { ApproveSubmissionRequest } from '../../shared/models/requests/approve-submission-request';
import {MatTab, MatTabGroup} from '@angular/material/tabs';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {MatCard} from '@angular/material/card';
import {CompetitionGameMasterService} from '../../services/competition/competition-game-master.service';
import {SubmissionGameMasterService} from '../../services/submission/submission-game-master.service';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-competition-admin-details',
  imports: [
    MatFormField,
    MatTabGroup,
    MatTab,
    MatLabel,
    MatButton,
    ReactiveFormsModule,
    MatInput,
    MatCard,
    NgForOf
  ],
  templateUrl: './competition-admin-details.component.html',
  styleUrl: './competition-admin-details.component.css'
})
export class CompetitionAdminDetailsComponent implements OnInit, OnChanges {
  @Input() competition!: CompetitionDto;
  @Output() reload = new EventEmitter<void>();

  editForm: FormGroup;
  scoreboard: ScoreEntryDto[] = [];
  submissions: SubmissionDto[] = [];
  loading = true;

  constructor(
    private fb: FormBuilder,
    private gmService: CompetitionGameMasterService,
    private gmSubService: SubmissionGameMasterService
  ) {
    this.editForm = this.fb.group({
      name: [''],
      description: [''],
      deadline: ['']
    });
  }

  ngOnInit() {
    this.loadAll();
  }
  ngOnChanges() {
    this.patchForm();
    this.loadAll();
  }

  patchForm() {
    if (this.competition) {
      this.editForm.patchValue({
        name: this.competition.name,
        description: this.competition.description,
        deadline: this.competition.deadline ? new Date(this.competition.deadline).toISOString().substring(0, 10) : ''
      });
    }
  }

  loadAll() {
    if (!this.competition || !this.competition.id) return;
    this.loading = true;
    this.gmService.getScoreBoard(this.competition.id).subscribe({
      next: data => this.scoreboard = data,
      error: () => {}
    });
    this.gmService.getSubmissions(this.competition.id).subscribe({
      next: data => this.submissions = data,
      error: () => {}
    });
    this.loading = false;
  }

  onSave() {
    const { name, description, deadline } = this.editForm.value;
    this.gmService.updateCompetition({ id: this.competition.id, request: { name, description, deadline } })
      .subscribe({
        next: () => {
          alert('Sikeres mentés!');
          this.reload.emit();
          this.loadAll();
        },
        error: err => alert('Hiba: ' + (err.error?.message ?? err.message))
      });
  }

  onDeleteCompetition() {
    if (confirm('Biztosan törölni szeretnéd ezt a versenyt? Ez nem visszavonható!')) {
      this.gmService.deleteCompetition(this.competition.id)
        .subscribe({
          next: () => {
            alert('Verseny törölve!');
            // Átirányítás a versenylistára vagy emit, hogy a parent kezelje
            window.location.href = '/competitions'; // vagy router.navigate(['...'])
          },
          error: err => alert('Törlés sikertelen: ' + (err.error?.message ?? err.message))
        });
    }
  }


  onStopCompetition() {
    if (confirm('Biztos le akarod állítani a versenyt?')) {
      this.gmService.stopCompetition(this.competition.id)
        .subscribe({
          next: () => {
            alert('Verseny leállítva!');
            this.reload.emit();
            this.loadAll();
          },
          error: err => alert('Leállítás sikertelen: ' + (err.error?.message ?? err.message))
        });
    }
  }

  approveSubmission(sub: SubmissionDto, approved: boolean, reviewerComment: string) {
    const req: ApproveSubmissionRequest = { approved, reviewerComment };
    this.gmSubService.approveSubmission(sub.id, req)
      .subscribe({
        next: () => {
          alert('Submission kezelve!');
          this.reload.emit();
          this.loadAll();
        },
        error: err => alert('Hiba: ' + (err.error?.message ?? err.message))
      });
  }
}
