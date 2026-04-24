import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FloorcodeGet } from './floorcode-get';

describe('FloorcodeGet', () => {
  let component: FloorcodeGet;
  let fixture: ComponentFixture<FloorcodeGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FloorcodeGet],
    }).compileComponents();

    fixture = TestBed.createComponent(FloorcodeGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
