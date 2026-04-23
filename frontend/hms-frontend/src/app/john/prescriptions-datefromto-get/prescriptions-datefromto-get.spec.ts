import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrescriptionsDatefromtoGet } from './prescriptions-datefromto-get';

describe('PrescriptionsDatefromtoGet', () => {
  let component: PrescriptionsDatefromtoGet;
  let fixture: ComponentFixture<PrescriptionsDatefromtoGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrescriptionsDatefromtoGet],
    }).compileComponents();

    fixture = TestBed.createComponent(PrescriptionsDatefromtoGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
