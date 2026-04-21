import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreatmentGet } from './treatment-get';

describe('TreatmentGet', () => {
  let component: TreatmentGet;
  let fixture: ComponentFixture<TreatmentGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TreatmentGet],
    }).compileComponents();

    fixture = TestBed.createComponent(TreatmentGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
