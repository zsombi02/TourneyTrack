import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionAdminDetailsComponent } from './competition-admin-details.component';

describe('CompetitionAdminDetailsComponent', () => {
  let component: CompetitionAdminDetailsComponent;
  let fixture: ComponentFixture<CompetitionAdminDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompetitionAdminDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompetitionAdminDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
