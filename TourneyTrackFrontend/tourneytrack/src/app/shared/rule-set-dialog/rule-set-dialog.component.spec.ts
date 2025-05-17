import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RuleSetDialogComponent } from './rule-set-dialog.component';

describe('RuleSetDialogComponent', () => {
  let component: RuleSetDialogComponent;
  let fixture: ComponentFixture<RuleSetDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RuleSetDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RuleSetDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
