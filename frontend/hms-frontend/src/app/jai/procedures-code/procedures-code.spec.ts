import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProceduresCode } from './procedures-code';

describe('ProceduresCode', () => {
  let component: ProceduresCode;
  let fixture: ComponentFixture<ProceduresCode>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProceduresCode],
    }).compileComponents();

    fixture = TestBed.createComponent(ProceduresCode);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
