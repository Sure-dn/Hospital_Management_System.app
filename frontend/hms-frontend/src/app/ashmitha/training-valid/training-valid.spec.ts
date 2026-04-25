import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingValid } from './training-valid';

describe('TrainingValid', () => {
  let component: TrainingValid;
  let fixture: ComponentFixture<TrainingValid>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrainingValid],
    }).compileComponents();

    fixture = TestBed.createComponent(TrainingValid);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
