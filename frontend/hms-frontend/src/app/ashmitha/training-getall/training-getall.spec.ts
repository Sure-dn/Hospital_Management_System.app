import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingGetall } from './training-getall';

describe('TrainingGetall', () => {
  let component: TrainingGetall;
  let fixture: ComponentFixture<TrainingGetall>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrainingGetall],
    }).compileComponents();

    fixture = TestBed.createComponent(TrainingGetall);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
