import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProceduresGet } from './procedures-get';

describe('ProceduresGet', () => {
  let component: ProceduresGet;
  let fixture: ComponentFixture<ProceduresGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProceduresGet],
    }).compileComponents();

    fixture = TestBed.createComponent(ProceduresGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
