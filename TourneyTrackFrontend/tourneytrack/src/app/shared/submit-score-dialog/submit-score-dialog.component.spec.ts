import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitScoreDialogComponent } from './submit-score-dialog.component';

describe('SubmitScoreDialogComponent', () => {
  let component: SubmitScoreDialogComponent;
  let fixture: ComponentFixture<SubmitScoreDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubmitScoreDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubmitScoreDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
