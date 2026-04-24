import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrescriptionsGet } from './prescriptions-get';

describe('PrescriptionsGet', () => {
  let component: PrescriptionsGet;
  let fixture: ComponentFixture<PrescriptionsGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrescriptionsGet],
    }).compileComponents();

    fixture = TestBed.createComponent(PrescriptionsGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
