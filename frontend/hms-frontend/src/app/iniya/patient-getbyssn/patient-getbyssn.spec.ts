import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientGetbyssn } from './patient-getbyssn';

describe('PatientGetbyssn', () => {
  let component: PatientGetbyssn;
  let fixture: ComponentFixture<PatientGetbyssn>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientGetbyssn],
    }).compileComponents();

    fixture = TestBed.createComponent(PatientGetbyssn);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
