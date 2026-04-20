import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Iniya } from './iniya';

describe('Iniya', () => {
  let component: Iniya;
  let fixture: ComponentFixture<Iniya>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Iniya],
    }).compileComponents();

    fixture = TestBed.createComponent(Iniya);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
