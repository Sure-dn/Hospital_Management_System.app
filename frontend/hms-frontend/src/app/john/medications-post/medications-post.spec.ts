import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicationsPost } from './medications-post';

describe('MedicationsPost', () => {
  let component: MedicationsPost;
  let fixture: ComponentFixture<MedicationsPost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicationsPost],
    }).compileComponents();

    fixture = TestBed.createComponent(MedicationsPost);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
