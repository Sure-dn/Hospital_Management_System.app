import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OncallPost } from './oncall-post';

describe('OncallPost', () => {
  let component: OncallPost;
  let fixture: ComponentFixture<OncallPost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OncallPost],
    }).compileComponents();

    fixture = TestBed.createComponent(OncallPost);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
