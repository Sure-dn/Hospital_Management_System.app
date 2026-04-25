import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentGetbydate } from './appointment-getbydate';

describe('AppointmentGetbydate', () => {
  let component: AppointmentGetbydate;
  let fixture: ComponentFixture<AppointmentGetbydate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentGetbydate],
    }).compileComponents();

    fixture = TestBed.createComponent(AppointmentGetbydate);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
