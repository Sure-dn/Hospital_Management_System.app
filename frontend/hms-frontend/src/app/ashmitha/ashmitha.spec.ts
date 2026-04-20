import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Ashmitha } from './ashmitha';

describe('Ashmitha', () => {
  let component: Ashmitha;
  let fixture: ComponentFixture<Ashmitha>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Ashmitha],
    }).compileComponents();

    fixture = TestBed.createComponent(Ashmitha);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
