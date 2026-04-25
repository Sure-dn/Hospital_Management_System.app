import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentPost } from './department-post';

describe('DepartmentPost', () => {
  let component: DepartmentPost;
  let fixture: ComponentFixture<DepartmentPost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DepartmentPost],
    }).compileComponents();

    fixture = TestBed.createComponent(DepartmentPost);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
