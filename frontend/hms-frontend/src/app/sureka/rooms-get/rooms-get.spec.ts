import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomsGet } from './rooms-get';

describe('RoomsGet', () => {
  let component: RoomsGet;
  let fixture: ComponentFixture<RoomsGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoomsGet],
    }).compileComponents();

    fixture = TestBed.createComponent(RoomsGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
