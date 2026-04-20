import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Jai } from './jai';

describe('Jai', () => {
  let component: Jai;
  let fixture: ComponentFixture<Jai>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Jai],
    }).compileComponents();

    fixture = TestBed.createComponent(Jai);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
