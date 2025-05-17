import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {RuleSetDto} from '../../shared/models/rule-set.dto';
import { RuleSetGameMasterService } from '../../services/ruleset/rule-set-game-master.service';
import {CompetitionGameMasterService} from '../../services/competition/competition-game-master.service';
import {MatDialog, MatDialogActions} from '@angular/material/dialog';
import {RuleSetDialogComponent} from '../../shared/rule-set-dialog/rule-set-dialog.component';
import {MatCard} from '@angular/material/card';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatOption} from '@angular/material/core';
import {Router} from '@angular/router';
import {MatButton} from '@angular/material/button';
import {MatSelect} from '@angular/material/select';
import {CreateCompetitionRequest} from '../../shared/models/requests/create-competition-request';
import {SessionService} from '../../services/session/session.service';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-create-competition',
  imports: [
    MatCard,
    MatFormField,
    MatLabel,
    MatOption,
    MatInput,
    ReactiveFormsModule,
    MatSelect,
    MatButton,
    MatDialogActions,
    NgForOf
  ],
  templateUrl: './create-competition.component.html',
  styleUrl: './create-competition.component.css'
})
export class CreateCompetitionComponent implements OnInit {
  form: FormGroup;
  ruleSets: RuleSetDto[] = [];
  selectedRuleSets: RuleSetDto[] = [];
  loading = false;

  constructor(
    private fb: FormBuilder,
    private ruleSetService: RuleSetGameMasterService,
    private competitionService: CompetitionGameMasterService,
    protected session: SessionService,
    private dialog: MatDialog,
    protected router: Router
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      deadline: ['', Validators.required],
      ruleSets: [[], Validators.required]
    });
  }

  ngOnInit() {
    this.loadRuleSets();
  }

  loadRuleSets() {
    this.ruleSetService.listAll().subscribe({
      next: (data) => this.ruleSets = data,
      error: () => this.ruleSets = []
    });
  }

  addRuleSet() {
    const dialogRef = this.dialog.open(RuleSetDialogComponent, {
      width: '600px',
      data: null // Új ruleset
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loadRuleSets(); // Frissíted a listát
      }
    });
  }

  onRuleSetsChange(event: any) {
    // Ha kell külön logika, pl. automatikusan hozzáadni a form-hoz a kiválasztottakat
  }

  onSubmit() {
    if (this.form.invalid) return;

    this.loading = true;
    const value = this.form.value;

    const req: CreateCompetitionRequest = {
      name: value.name,
      description: value.description,
      deadline: value.deadline,
      ruleSetIds: value.ruleSets.map((rs: any) => rs.id),
      gameMasterId: this.session.getUserId()
    };

    this.competitionService.createCompetition(req).subscribe({
      next: (competition) => {
        this.loading = false;
        alert('Verseny létrehozva!');
        this.router.navigate(['/competitions', competition.id]);
      },
      error: err => {
        this.loading = false;
        alert('Hiba: ' + (err.error?.message ?? err.message));
      }
    });
  }

}
