import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcedurePatients } from './procedure-patients';

describe('ProcedurePatients', () => {
  let component: ProcedurePatients;
  let fixture: ComponentFixture<ProcedurePatients>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProcedurePatients],
    }).compileComponents();

    fixture = TestBed.createComponent(ProcedurePatients);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
