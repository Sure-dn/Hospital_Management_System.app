import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomnoGet } from './roomno-get';

describe('RoomnoGet', () => {
  let component: RoomnoGet;
  let fixture: ComponentFixture<RoomnoGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoomnoGet],
    }).compileComponents();

    fixture = TestBed.createComponent(RoomnoGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
