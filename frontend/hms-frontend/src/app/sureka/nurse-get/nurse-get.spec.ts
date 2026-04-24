import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NurseGet } from './nurse-get';

describe('NurseGet', () => {
  let component: NurseGet;
  let fixture: ComponentFixture<NurseGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NurseGet],
    }).compileComponents();

    fixture = TestBed.createComponent(NurseGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
