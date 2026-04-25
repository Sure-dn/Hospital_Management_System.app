import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentGetbyssn } from './appointment-getbyssn';

describe('AppointmentGetbyssn', () => {
  let component: AppointmentGetbyssn;
  let fixture: ComponentFixture<AppointmentGetbyssn>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentGetbyssn],
    }).compileComponents();

    fixture = TestBed.createComponent(AppointmentGetbyssn);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
