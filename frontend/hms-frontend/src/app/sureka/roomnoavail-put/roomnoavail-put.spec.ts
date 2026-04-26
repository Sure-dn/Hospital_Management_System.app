import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomnoavailPut } from './roomnoavail-put';

describe('RoomnoavailPut', () => {
  let component: RoomnoavailPut;
  let fixture: ComponentFixture<RoomnoavailPut>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoomnoavailPut],
    }).compileComponents();

    fixture = TestBed.createComponent(RoomnoavailPut);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
