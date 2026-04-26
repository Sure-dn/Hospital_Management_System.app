import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhysicianUpdate } from './physician-update';

describe('PhysicianUpdate', () => {
  let component: PhysicianUpdate;
  let fixture: ComponentFixture<PhysicianUpdate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PhysicianUpdate],
    }).compileComponents();

    fixture = TestBed.createComponent(PhysicianUpdate);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
