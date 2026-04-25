import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientGet } from './patient-get';

describe('PatientGet', () => {
  let component: PatientGet;
  let fixture: ComponentFixture<PatientGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientGet],
    }).compileComponents();

    fixture = TestBed.createComponent(PatientGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
