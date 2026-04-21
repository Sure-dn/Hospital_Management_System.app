import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JaiComponent } from './jai';

describe('JaiComponent', () => {
  let component: JaiComponent;
  let fixture: ComponentFixture<JaiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JaiComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(JaiComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
