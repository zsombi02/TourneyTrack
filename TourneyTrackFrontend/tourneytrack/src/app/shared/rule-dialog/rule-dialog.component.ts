import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogActions, MatDialogRef} from '@angular/material/dialog';
import {RuleSetDto} from '../models/rule-set.dto';
import {RuleSetGameMasterService} from '../../services/ruleset/rule-set-game-master.service';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {UpdateRuleRequest} from '../models/requests/update-rule-request';
import {CreateRuleRequest} from '../models/requests/create-rule-request';


@Component({
  selector: 'app-rule-dialog',
  imports: [
    MatFormField,
    ReactiveFormsModule,
    MatInput,
    MatDialogActions,
    MatButton,
    MatLabel
  ],
  templateUrl: './rule-dialog.component.html',
  styleUrl: './rule-dialog.component.css'
})
export class RuleDialogComponent {
  form: FormGroup;
  isEdit: boolean;

  constructor(
    public dialogRef: MatDialogRef<RuleDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { ruleset: RuleSetDto, rule: any },
    private fb: FormBuilder,
    private service: RuleSetGameMasterService
  ) {
    this.isEdit = !!data.rule;
    this.form = this.fb.group({
      name: [data.rule ? data.rule.name : '', Validators.required],
      description: [data.rule ? data.rule.description : '', Validators.required],
      points: [data.rule ? data.rule.points : 1, [Validators.required, Validators.min(1)]],
    });
  }

  onSubmit() {
    if (this.form.invalid) return;
    if (this.isEdit) {
      const req: UpdateRuleRequest = { ...this.form.value };
      this.service.updateRuleInRuleSet(this.data.ruleset.id, this.data.rule.id, req).subscribe({
        next: () => this.dialogRef.close(true),
        error: err => alert(err.error?.message ?? err.message)
      });
    } else {
      const req: CreateRuleRequest = { ...this.form.value };
      this.service.addRuleToRuleSet(this.data.ruleset.id, req).subscribe({
        next: () => this.dialogRef.close(true),
        error: err => alert(err.error?.message ?? err.message)
      });
    }
  }

  onCancel() {
    this.dialogRef.close();
  }
}
