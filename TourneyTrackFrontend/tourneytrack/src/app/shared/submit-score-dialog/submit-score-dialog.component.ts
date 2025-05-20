import { Component, Inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {RuleDto} from '../models/rule.dto';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {FormsModule} from '@angular/forms';
import {NgForOf} from '@angular/common';
import {MatButton} from '@angular/material/button';
import {MatOption} from '@angular/material/core';
import {MatSelect} from '@angular/material/select';

@Component({
  selector: 'app-submit-score-dialog',
  templateUrl: './submit-score-dialog.component.html',
  imports: [
    MatDialogActions,
    MatLabel,
    MatDialogTitle,
    MatFormField,
    MatDialogContent,
    MatOption,
    FormsModule,
    NgForOf,
    MatButton,
    MatInput,
    MatSelect
  ],
  styleUrl: './submit-score-dialog.component.css'
})
export class SubmitScoreDialogComponent {
  selectedRuleId: number | null = null;
  description: string = '';

  allowedRules: RuleDto[] = [];

  constructor(
    public dialogRef: MatDialogRef<SubmitScoreDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { rules: RuleDto[], userSubmissions: any[] }
  ) {
    const submittedRuleIds = new Set(
      data.userSubmissions.map(sub => sub.rule.id)
    );
    this.allowedRules = data.rules.filter(rule => !submittedRuleIds.has(rule.id));
  }

  onSubmit() {
    if (!this.selectedRuleId || !this.description) {
      return;
    }
    this.dialogRef.close({
      ruleId: this.selectedRuleId,
      description: this.description
    });
  }

  onCancel() {
    this.dialogRef.close();
  }
}
