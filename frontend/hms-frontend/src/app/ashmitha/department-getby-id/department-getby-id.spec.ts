import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentGetbyID } from './department-getby-id';

describe('DepartmentGetbyID', () => {
  let component: DepartmentGetbyID;
  let fixture: ComponentFixture<DepartmentGetbyID>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DepartmentGetbyID],
    }).compileComponents();

    fixture = TestBed.createComponent(DepartmentGetbyID);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
