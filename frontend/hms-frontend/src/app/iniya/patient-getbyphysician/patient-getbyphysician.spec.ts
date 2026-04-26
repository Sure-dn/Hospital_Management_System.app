import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientGetbyphysician } from './patient-getbyphysician';

describe('PatientGetbyphysician', () => {
  let component: PatientGetbyphysician;
  let fixture: ComponentFixture<PatientGetbyphysician>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientGetbyphysician],
    }).compileComponents();

    fixture = TestBed.createComponent(PatientGetbyphysician);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
