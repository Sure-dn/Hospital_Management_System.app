import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientPutPcpComponent } from './patient-putbyphysician';

describe('PatientPutbyphysician', () => {
  let component: PatientPutPcpComponent;
  let fixture: ComponentFixture<PatientPutPcpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientPutPcpComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PatientPutPcpComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
