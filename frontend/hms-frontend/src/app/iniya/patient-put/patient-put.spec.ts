import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientPut } from './patient-put';

describe('PatientPut', () => {
  let component: PatientPut;
  let fixture: ComponentFixture<PatientPut>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientPut],
    }).compileComponents();

    fixture = TestBed.createComponent(PatientPut);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
