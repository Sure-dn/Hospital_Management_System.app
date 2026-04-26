import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppointmentGetComponent } from './appointment-get';

describe('AppointmentGetComponent', () => {
  let component: AppointmentGetComponent;
  let fixture: ComponentFixture<AppointmentGetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentGetComponent], // standalone component
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentGetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});