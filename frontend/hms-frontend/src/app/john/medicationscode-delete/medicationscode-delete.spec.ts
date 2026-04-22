import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationscodeDelete } from './medicationscode-delete';

describe('MedicationscodeDelete', () => {
  let component: MedicationscodeDelete;
  let fixture: ComponentFixture<MedicationscodeDelete>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicationscodeDelete],
    }).compileComponents();

    fixture = TestBed.createComponent(MedicationscodeDelete);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
