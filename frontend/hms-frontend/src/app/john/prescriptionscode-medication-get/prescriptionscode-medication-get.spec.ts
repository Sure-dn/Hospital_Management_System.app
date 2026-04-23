import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrescriptionscodeMedicationGet } from './prescriptionscode-medication-get';

describe('PrescriptionscodeMedicationGet', () => {
  let component: PrescriptionscodeMedicationGet;
  let fixture: ComponentFixture<PrescriptionscodeMedicationGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrescriptionscodeMedicationGet],
    }).compileComponents();

    fixture = TestBed.createComponent(PrescriptionscodeMedicationGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
