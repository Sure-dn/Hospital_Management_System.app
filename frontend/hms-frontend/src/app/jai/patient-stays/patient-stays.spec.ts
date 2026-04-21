import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientStays } from './patient-stays';

describe('PatientStays', () => {
  let component: PatientStays;
  let fixture: ComponentFixture<PatientStays>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientStays],
    }).compileComponents();

    fixture = TestBed.createComponent(PatientStays);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
