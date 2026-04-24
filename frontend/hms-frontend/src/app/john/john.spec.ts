import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JohnComponent } from './john';

describe('John', () => {
  let component: JohnComponent;
  let fixture: ComponentFixture<JohnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JohnComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(JohnComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
