import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentPost } from './appointment-post';

describe('AppointmentPost', () => {
  let component: AppointmentPost;
  let fixture: ComponentFixture<AppointmentPost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentPost],
    }).compileComponents();

    fixture = TestBed.createComponent(AppointmentPost);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
