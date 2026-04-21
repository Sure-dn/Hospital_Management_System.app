import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhysicianProceduresComponent } from './physician-procedures';

describe('PhysicianProcedures', () => {
  let component: PhysicianProceduresComponent;
  let fixture: ComponentFixture<PhysicianProceduresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PhysicianProceduresComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PhysicianProceduresComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
