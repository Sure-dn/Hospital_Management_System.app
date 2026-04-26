import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhysicianGet } from './physician-get';

describe('PhysicianGet', () => {
  let component: PhysicianGet;
  let fixture: ComponentFixture<PhysicianGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PhysicianGet],
    }).compileComponents();

    fixture = TestBed.createComponent(PhysicianGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
