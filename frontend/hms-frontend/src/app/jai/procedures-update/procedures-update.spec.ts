import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProceduresUpdate } from './procedures-update';

describe('ProceduresUpdate', () => {
  let component: ProceduresUpdate;
  let fixture: ComponentFixture<ProceduresUpdate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProceduresUpdate],
    }).compileComponents();

    fixture = TestBed.createComponent(ProceduresUpdate);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
