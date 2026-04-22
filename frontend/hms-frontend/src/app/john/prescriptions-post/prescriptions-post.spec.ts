import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrescriptionsPost } from './prescriptions-post';

describe('PrescriptionsPost', () => {
  let component: PrescriptionsPost;
  let fixture: ComponentFixture<PrescriptionsPost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrescriptionsPost],
    }).compileComponents();

    fixture = TestBed.createComponent(PrescriptionsPost);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
