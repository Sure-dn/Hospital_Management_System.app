import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhysicianGetbyid } from './physician-getbyid';

describe('PhysicianGetbyid', () => {
  let component: PhysicianGetbyid;
  let fixture: ComponentFixture<PhysicianGetbyid>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PhysicianGetbyid],
    }).compileComponents();

    fixture = TestBed.createComponent(PhysicianGetbyid);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
