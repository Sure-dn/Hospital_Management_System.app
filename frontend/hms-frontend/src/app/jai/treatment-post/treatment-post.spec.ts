import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreatmentPost } from './treatment-post';

describe('TreatmentPost', () => {
  let component: TreatmentPost;
  let fixture: ComponentFixture<TreatmentPost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TreatmentPost],
    }).compileComponents();

    fixture = TestBed.createComponent(TreatmentPost);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
