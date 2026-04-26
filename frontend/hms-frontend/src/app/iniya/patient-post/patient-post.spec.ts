import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientPost } from './patient-post';

describe('PatientPost', () => {
  let component: PatientPost;
  let fixture: ComponentFixture<PatientPost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientPost],
    }).compileComponents();

    fixture = TestBed.createComponent(PatientPost);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
