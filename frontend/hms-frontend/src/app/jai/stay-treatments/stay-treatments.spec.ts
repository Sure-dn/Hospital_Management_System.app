import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StayTreatments } from './stay-treatments';

describe('StayTreatments', () => {
  let component: StayTreatments;
  let fixture: ComponentFixture<StayTreatments>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StayTreatments],
    }).compileComponents();

    fixture = TestBed.createComponent(StayTreatments);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
