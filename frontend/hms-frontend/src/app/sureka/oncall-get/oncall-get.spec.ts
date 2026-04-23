import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OncallGet } from './oncall-get';

describe('OncallGet', () => {
  let component: OncallGet;
  let fixture: ComponentFixture<OncallGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OncallGet],
    }).compileComponents();

    fixture = TestBed.createComponent(OncallGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
