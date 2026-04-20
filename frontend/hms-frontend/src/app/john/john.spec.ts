import { ComponentFixture, TestBed } from '@angular/core/testing';

import { John } from './john';

describe('John', () => {
  let component: John;
  let fixture: ComponentFixture<John>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [John],
    }).compileComponents();

    fixture = TestBed.createComponent(John);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
