import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhysicianDelete } from './physician-delete';

describe('PhysicianDelete', () => {
  let component: PhysicianDelete;
  let fixture: ComponentFixture<PhysicianDelete>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PhysicianDelete],
    }).compileComponents();

    fixture = TestBed.createComponent(PhysicianDelete);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
