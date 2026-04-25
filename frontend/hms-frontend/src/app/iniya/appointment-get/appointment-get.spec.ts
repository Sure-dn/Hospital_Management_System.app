import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentGet } from './appointment-get';

describe('AppointmentGet', () => {
  let component: AppointmentGet;
  let fixture: ComponentFixture<AppointmentGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentGet],
    }).compileComponents();

    fixture = TestBed.createComponent(AppointmentGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
