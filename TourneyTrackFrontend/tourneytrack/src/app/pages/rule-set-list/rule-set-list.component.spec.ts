import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RuleSetListComponent } from './rule-set-list.component';

describe('RuleSetListComponent', () => {
  let component: RuleSetListComponent;
  let fixture: ComponentFixture<RuleSetListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RuleSetListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RuleSetListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
