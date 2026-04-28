import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnCallPostComponent } from './oncall-post';

describe('OncallPost', () => {
  let component: OnCallPostComponent;
  let fixture: ComponentFixture<OnCallPostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnCallPostComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(OnCallPostComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
