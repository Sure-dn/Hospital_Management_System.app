import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Sureka } from './sureka';

describe('Sureka', () => {
  let component: Sureka;
  let fixture: ComponentFixture<Sureka>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Sureka],
    }).compileComponents();

    fixture = TestBed.createComponent(Sureka);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
