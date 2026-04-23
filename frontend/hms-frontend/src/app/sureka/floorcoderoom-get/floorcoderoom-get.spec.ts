import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FloorcoderoomGet } from './floorcoderoom-get';

describe('FloorcoderoomGet', () => {
  let component: FloorcoderoomGet;
  let fixture: ComponentFixture<FloorcoderoomGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FloorcoderoomGet],
    }).compileComponents();

    fixture = TestBed.createComponent(FloorcoderoomGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
