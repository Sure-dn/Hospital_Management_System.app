import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OncallDelete } from './oncall-delete';

describe('OncallDelete', () => {
  let component: OncallDelete;
  let fixture: ComponentFixture<OncallDelete>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OncallDelete],
    }).compileComponents();

    fixture = TestBed.createComponent(OncallDelete);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
