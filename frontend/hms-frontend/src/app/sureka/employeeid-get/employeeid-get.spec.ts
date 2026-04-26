import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeidGet } from './employeeid-get';

describe('EmployeeidGet', () => {
  let component: EmployeeidGet;
  let fixture: ComponentFixture<EmployeeidGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeidGet],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeeidGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
