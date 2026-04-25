import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhysicianPost } from './physician-post';

describe('PhysicianPost', () => {
  let component: PhysicianPost;
  let fixture: ComponentFixture<PhysicianPost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PhysicianPost],
    }).compileComponents();

    fixture = TestBed.createComponent(PhysicianPost);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
