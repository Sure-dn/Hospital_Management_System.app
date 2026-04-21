import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProceduresPostComponent } from './procedures-post';

describe('ProceduresPost', () => {
  let component: ProceduresPostComponent;
  let fixture: ComponentFixture<ProceduresPostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProceduresPostComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ProceduresPostComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
