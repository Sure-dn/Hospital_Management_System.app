import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeidPut } from './employeeid-put';

describe('EmployeeidPut', () => {
  let component: EmployeeidPut;
  let fixture: ComponentFixture<EmployeeidPut>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeidPut],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeeidPut);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
