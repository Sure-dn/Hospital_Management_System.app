import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationscodePut } from './medicationscode-put';

describe('MedicationscodePut', () => {
  let component: MedicationscodePut;
  let fixture: ComponentFixture<MedicationscodePut>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicationscodePut],
    }).compileComponents();

    fixture = TestBed.createComponent(MedicationscodePut);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
