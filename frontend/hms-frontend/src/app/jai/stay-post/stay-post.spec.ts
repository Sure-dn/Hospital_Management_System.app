import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StayPost } from './stay-post';

describe('StayPost', () => {
  let component: StayPost;
  let fixture: ComponentFixture<StayPost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StayPost],
    }).compileComponents();

    fixture = TestBed.createComponent(StayPost);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
