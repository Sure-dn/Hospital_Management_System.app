import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AffiliationGetbyphysician } from './affiliation-getbyphysician';

describe('AffiliationGetbyphysician', () => {
  let component: AffiliationGetbyphysician;
  let fixture: ComponentFixture<AffiliationGetbyphysician>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AffiliationGetbyphysician],
    }).compileComponents();

    fixture = TestBed.createComponent(AffiliationGetbyphysician);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
