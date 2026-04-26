import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentGetByPhysicianComponent } from './appointment-getbyphysicianid';

describe('AppointmentGetbyphysicianid', () => {
  let component: AppointmentGetByPhysicianComponent;
  let fixture: ComponentFixture<AppointmentGetByPhysicianComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentGetByPhysicianComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(AppointmentGetByPhysicianComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
