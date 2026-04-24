import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AffiliationGetbydepartment } from './affiliation-getbydepartment';

describe('AffiliationGetbydepartment', () => {
  let component: AffiliationGetbydepartment;
  let fixture: ComponentFixture<AffiliationGetbydepartment>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AffiliationGetbydepartment],
    }).compileComponents();

    fixture = TestBed.createComponent(AffiliationGetbydepartment);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
