import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrescriptionsPatientssnGet } from './prescriptions-patientssn-get';

describe('PrescriptionsPatientssnGet', () => {
  let component: PrescriptionsPatientssnGet;
  let fixture: ComponentFixture<PrescriptionsPatientssnGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrescriptionsPatientssnGet],
    }).compileComponents();

    fixture = TestBed.createComponent(PrescriptionsPatientssnGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
