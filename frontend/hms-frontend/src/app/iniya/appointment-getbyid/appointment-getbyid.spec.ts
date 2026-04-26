import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentGetbyid } from './appointment-getbyid';

describe('AppointmentGetbyid', () => {
  let component: AppointmentGetbyid;
  let fixture: ComponentFixture<AppointmentGetbyid>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentGetbyid],
    }).compileComponents();

    fixture = TestBed.createComponent(AppointmentGetbyid);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
