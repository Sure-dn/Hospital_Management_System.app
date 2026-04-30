import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllNursesComponent } from './nurse-get';

describe('NurseGet', () => {
  let component: GetAllNursesComponent;
  let fixture: ComponentFixture<GetAllNursesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetAllNursesComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(GetAllNursesComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
