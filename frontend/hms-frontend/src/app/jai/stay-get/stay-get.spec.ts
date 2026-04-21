import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StayGet } from './stay-get';

describe('StayGet', () => {
  let component: StayGet;
  let fixture: ComponentFixture<StayGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StayGet],
    }).compileComponents();

    fixture = TestBed.createComponent(StayGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
