import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationscodeGet } from './medicationscode-get';

describe('MedicationscodeGet', () => {
  let component: MedicationscodeGet;
  let fixture: ComponentFixture<MedicationscodeGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicationscodeGet],
    }).compileComponents();

    fixture = TestBed.createComponent(MedicationscodeGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
