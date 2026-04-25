import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentDeletebyid } from './appointment-deletebyid';

describe('AppointmentDeletebyid', () => {
  let component: AppointmentDeletebyid;
  let fixture: ComponentFixture<AppointmentDeletebyid>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentDeletebyid],
    }).compileComponents();

    fixture = TestBed.createComponent(AppointmentDeletebyid);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
