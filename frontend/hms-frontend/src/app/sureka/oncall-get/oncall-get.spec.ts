import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnCallGetComponent } from './oncall-get';

describe('OncallGet', () => {
  let component: OnCallGetComponent;
  let fixture: ComponentFixture<OnCallGetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnCallGetComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(OnCallGetComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
