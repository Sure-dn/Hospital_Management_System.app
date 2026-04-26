import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentGetall } from './department-getall';

describe('DepartmentGetall', () => {
  let component: DepartmentGetall;
  let fixture: ComponentFixture<DepartmentGetall>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DepartmentGetall],
    }).compileComponents();

    fixture = TestBed.createComponent(DepartmentGetall);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
