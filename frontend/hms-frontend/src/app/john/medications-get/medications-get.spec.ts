import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationsGet } from './medications-get';

describe('MedicationsGet', () => {
  let component: MedicationsGet;
  let fixture: ComponentFixture<MedicationsGet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicationsGet],
    }).compileComponents();

    fixture = TestBed.createComponent(MedicationsGet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
