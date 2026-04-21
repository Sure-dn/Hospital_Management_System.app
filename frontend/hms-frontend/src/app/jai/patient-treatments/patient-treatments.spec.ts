import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientTreatments } from './patient-treatments';

describe('PatientTreatments', () => {
  let component: PatientTreatments;
  let fixture: ComponentFixture<PatientTreatments>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientTreatments],
    }).compileComponents();

    fixture = TestBed.createComponent(PatientTreatments);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
