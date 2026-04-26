import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingPost } from './training-post';

describe('TrainingPost', () => {
  let component: TrainingPost;
  let fixture: ComponentFixture<TrainingPost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrainingPost],
    }).compileComponents();

    fixture = TestBed.createComponent(TrainingPost);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
