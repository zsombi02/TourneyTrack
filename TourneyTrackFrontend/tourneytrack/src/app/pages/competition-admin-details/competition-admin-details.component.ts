import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {CompetitionDto} from '../../shared/models/competition.dto';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatTab, MatTabGroup} from '@angular/material/tabs';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatButton, MatIconButton} from '@angular/material/button';
import {CompetitionGameMasterService} from '../../services/competition/competition-game-master.service';
import {DatePipe, NgClass, NgForOf, NgIf} from '@angular/common';
import {MatDivider} from '@angular/material/divider';
import {MatIcon} from '@angular/material/icon';
import {MatSelect} from '@angular/material/select';
import {MatOption} from '@angular/material/core';
import {RuleSetGameMasterService} from '../../services/ruleset/rule-set-game-master.service';
import {RuleSetDto} from '../../shared/models/rule-set.dto';
import {MatError} from '@angular/material/form-field';
import {SubmissionDto} from '../../shared/models/submission.dto';
import {ApproveSubmissionRequest} from '../../shared/models/requests/approve-submission-request';
import {SubmissionGameMasterService} from '../../services/submission/submission-game-master.service';

@Component({
  selector: 'app-competition-admin-details',
  imports: [
    MatFormField,
    MatLabel,
    MatButton,
    ReactiveFormsModule,
    MatInput,
    NgForOf,
    MatIcon,
    MatDivider,
    MatSelect,
    FormsModule,
    MatOption,
    MatTab,
    NgClass,
    MatTabGroup,
    DatePipe,
    NgIf,
    MatError,
    MatIconButton
  ],
  templateUrl: './competition-admin-details.component.html',
  styleUrl: './competition-admin-details.component.css'
})
export class CompetitionAdminDetailsComponent implements OnInit, OnChanges {
  @Input() competition!: CompetitionDto;
  @Output() reload = new EventEmitter<void>();
  submissions: SubmissionDto[] = [];

  editForm: FormGroup;

  minDateStr = ''; // yyyy-MM-dd, today
  selectedTab = 0;

  availableRuleSets: RuleSetDto[] = [];
  selectedRuleSetIds: number[] = [];
  assigning = false;
  reviewerComments: { [id: number]: string } = {};


  constructor(
    private fb: FormBuilder,
    private gmService: CompetitionGameMasterService,
    private ruleSetService: RuleSetGameMasterService,
    private submissionService: SubmissionGameMasterService
  ) {
    this.editForm = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      deadline: ['', [Validators.required]],
    });
  }

  ngOnInit() {
    this.updateMinDate();
    this.patchForm();
    this.loadRuleSets();
    this.loadSubmissions();
  }

  ngOnChanges() {
    this.patchForm();
    this.loadRuleSets();
    this.updateMinDate();
  }

  get pendingSubmissions(): SubmissionDto[] {
    return this.submissions.filter(s => s.status === 'PENDING');
  }

  onApproveSubmission(sub: SubmissionDto, approved: boolean) {
    const comment = this.reviewerComments[sub.id] || '';
    const action = approved ? 'jóváhagyod' : 'elutasítod';
    const msg = `Biztosan ${action} ezt a beadást?\n\nFelhasználó: ${sub.user.name}\nSzabály: ${sub.rule.name}\nLeírás: ${sub.description}\n\nMegjegyzés: ${comment || '-'}`;
    if (!confirm(msg)) return;

    const req: ApproveSubmissionRequest = {
      approved,
      reviewerComment: comment
    };
    this.submissionService.approveSubmission(sub.id, req)
      .subscribe({
        next: () => {
          alert('Submission elbírálva!');
          this.reload.emit();
          this.loadSubmissions();
          // Esetleg töröld ki az inputot:
          this.reviewerComments[sub.id] = '';
        },
        error: err => alert('Hiba: ' + (err.error?.message ?? err.message))
      });
  }

  loadSubmissions() {
    if (!this.competition?.id) return;
    this.gmService.getSubmissions(this.competition.id).subscribe({
      next: data => this.submissions = data,
      error: () => {}
    });
  }




  updateMinDate() {
    const today = new Date();
    this.minDateStr = today.toISOString().substring(0, 10);
    this.editForm.get('deadline')?.setValidators([
      Validators.required,
      control => {
        if (!control.value) return null;
        return control.value >= this.minDateStr ? null : { min: true };
      }
    ]);
    this.editForm.get('deadline')?.updateValueAndValidity({ onlySelf: true, emitEvent: false });
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

  loadRuleSets() {
    this.ruleSetService.listAll().subscribe({
      next: data => this.availableRuleSets = data,
      error: () => { this.availableRuleSets = []; }
    });
  }

  onSave() {
    if (!this.competition?.id) return;
    if (this.editForm.invalid) {
      this.editForm.markAllAsTouched();
      return;
    }
    const { name, description, deadline } = this.editForm.value;
    this.gmService.updateCompetition({ id: this.competition.id, request: { name, description, deadline } })
      .subscribe({
        next: () => {
          alert('Sikeres mentés!');
          this.reload.emit();
        },
        error: err => alert('Hiba: ' + (err.error?.message ?? err.message))
      });
  }

  onDeleteCompetition() {
    if (!this.competition?.id) return;
    if (confirm('Biztosan törölni szeretnéd ezt a versenyt? Ez nem visszavonható!')) {
      this.gmService.deleteCompetition(this.competition.id)
        .subscribe({
          next: () => {
            alert('Verseny törölve!');
            window.location.href = '/competitions';
          },
          error: err => alert('Törlés sikertelen: ' + (err.error?.message ?? err.message))
        });
    }
  }

  onStopCompetition() {
    if (!this.competition?.id) return;
    if (confirm('Biztos le akarod állítani a versenyt?')) {
      this.gmService.stopCompetition(this.competition.id)
        .subscribe({
          next: () => {
            alert('Verseny leállítva!');
            this.reload.emit();
          },
          error: err => alert('Leállítás sikertelen: ' + (err.error?.message ?? err.message))
        });
    }
  }

  onAssignRuleSets() {
    if (!this.competition?.id || !this.selectedRuleSetIds.length) return;
    this.assigning = true;
    // Több szabálykészletet egymás után rendeljünk hozzá
    const addNext = (ids: number[]) => {
      if (!ids.length) {
        this.assigning = false;
        this.selectedRuleSetIds = [];
        this.reload.emit();
        return;
      }
      const nextId = ids.shift()!;
      this.gmService.assignRuleSet(this.competition.id, nextId).subscribe({
        next: () => addNext(ids),
        error: err => {
          alert('Hozzáadás sikertelen: ' + (err.error?.message ?? err.message));
          this.assigning = false;
        }
      });
    };
    addNext([...this.selectedRuleSetIds]);
  }

  onRemoveRuleSet(ruleSetId: number) {
    if (!this.competition?.id) return;
    if (confirm('Biztosan törlöd ezt a szabálykészletet a versenyből?')) {
      this.gmService.removeRuleSet(this.competition.id, ruleSetId).subscribe({
        next: () => {
          this.reload.emit();
        },
        error: err => alert('Törlés sikertelen: ' + (err.error?.message ?? err.message))
      });
    }
  }
}
