import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentGethead } from './department-gethead';

describe('DepartmentGethead', () => {
  let component: DepartmentGethead;
  let fixture: ComponentFixture<DepartmentGethead>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DepartmentGethead],
    }).compileComponents();

    fixture = TestBed.createComponent(DepartmentGethead);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
