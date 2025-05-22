import {Component, OnInit} from '@angular/core';
import {RuleSetDto} from '../../shared/models/rule-set.dto';
import {RuleSetGameMasterService} from '../../services/ruleset/rule-set-game-master.service';
import {MatDialog} from '@angular/material/dialog';
import {MatCard} from '@angular/material/card';
import {NgForOf, NgIf} from '@angular/common';
import {MatIcon} from '@angular/material/icon';
import {RuleSetDialogComponent} from '../../shared/rule-set-dialog/rule-set-dialog.component';
import {RuleDialogComponent} from '../../shared/rule-dialog/rule-dialog.component';
import {MatButton, MatIconButton} from '@angular/material/button';

@Component({
  selector: 'app-rule-set-list',
  imports: [
    MatCard,
    NgForOf,
    MatIcon,
    MatButton,
    MatIconButton,
    NgIf
  ],
  templateUrl: './rule-set-list.component.html',
  styleUrl: './rule-set-list.component.css'
})
export class RuleSetListComponent implements OnInit {
  rulesets: RuleSetDto[] = [];
  loading = true;

  constructor(
    private rulesetService: RuleSetGameMasterService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.loadAll();
  }

  loadAll() {
    this.rulesetService.listAll().subscribe({
      next: data => { this.rulesets = data; this.loading = false; },
      error: () => { this.loading = false; }
    });
  }

  openCreateDialog() {
    const dialogRef = this.dialog.open(RuleSetDialogComponent, { width: '500px', data: null });
    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadAll();
    });
  }

  openEditDialog(ruleset: RuleSetDto) {
    const dialogRef = this.dialog.open(RuleSetDialogComponent, { width: '500px', data: ruleset });
    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadAll();
    });
  }

  deleteRuleSet(id: number) {
    if (confirm('Biztos törölni akarod ezt a szabálykészletet?')) {
      this.rulesetService.deleteRuleSet(id).subscribe(() => this.loadAll());
    }
  }

  openAddRuleDialog(ruleset: RuleSetDto) {
    const dialogRef = this.dialog.open(RuleDialogComponent, { width: '400px', data: { ruleset, rule: null } });
    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadAll();
    });
  }

  openEditRuleDialog(ruleset: RuleSetDto, rule: any) {
    const dialogRef = this.dialog.open(RuleDialogComponent, { width: '400px', data: { ruleset, rule } });
    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadAll();
    });
  }

  deleteRule(rulesetId: number, ruleId: number) {
    if (confirm('Biztos törölni akarod ezt a szabályt?')) {
      this.rulesetService.deleteRuleFromRuleSet(rulesetId, ruleId).subscribe(() => this.loadAll());
    }
  }
}
