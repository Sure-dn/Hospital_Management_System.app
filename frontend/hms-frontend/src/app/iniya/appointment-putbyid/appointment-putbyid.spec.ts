import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentPutbyid } from './appointment-putbyid';

describe('AppointmentPutbyid', () => {
  let component: AppointmentPutbyid;
  let fixture: ComponentFixture<AppointmentPutbyid>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentPutbyid],
    }).compileComponents();

    fixture = TestBed.createComponent(AppointmentPutbyid);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
