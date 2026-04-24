import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrescriptionsPhysicianempidGet } from './prescriptions-physicianempid-get';

describe('PrescriptionsPhysicianempidGet', () => {
  let component: PrescriptionsPhysicianempidGet;
  let fixture: ComponentFixture<PrescriptionsPhysicianempidGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrescriptionsPhysicianempidGet],
    }).compileComponents();

    fixture = TestBed.createComponent(PrescriptionsPhysicianempidGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
