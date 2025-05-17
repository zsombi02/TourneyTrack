import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogActions, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {RuleSetDto} from '../models/rule-set.dto';
import {RuleSetGameMasterService} from '../../services/ruleset/rule-set-game-master.service';
import {CreateRuleSetRequest} from '../models/requests/create-rule-set-request';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-rule-set-dialog',
  imports: [
    ReactiveFormsModule,
    MatFormField,
    MatLabel,
    MatFormField,
    MatDialogActions,
    MatDialogTitle,
    MatInput,
    MatButton
  ],
  templateUrl: './rule-set-dialog.component.html',
  styleUrl: './rule-set-dialog.component.css'
})
export class RuleSetDialogComponent {
  form: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<RuleSetDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: RuleSetDto | null,
    private fb: FormBuilder,
    private service: RuleSetGameMasterService
  ) {
    this.form = this.fb.group({
      name: [data ? data.name : '', Validators.required]
    });
  }

  onSubmit() {
    if (this.form.invalid) return;
    const request: CreateRuleSetRequest = {
      name: this.form.value.name,
      rules: []
    };
    if (this.data) {
      this.service.updateRuleSet(this.data.id!, request).subscribe({
        next: () => this.dialogRef.close(true),
        error: err => alert(err.error?.message ?? err.message)
      });
    } else {
      this.service.createRuleSet(request).subscribe({
        next: () => this.dialogRef.close(true),
        error: err => alert(err.error?.message ?? err.message)
      });
    }
  }

  onCancel() {
    this.dialogRef.close();
  }
}
