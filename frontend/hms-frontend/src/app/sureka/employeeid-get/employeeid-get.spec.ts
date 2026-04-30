import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetNurseByIdComponent } from './employeeid-get';

describe('EmployeeidGet', () => {
  let component: GetNurseByIdComponent;
  let fixture: ComponentFixture<GetNurseByIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetNurseByIdComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(GetNurseByIdComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
