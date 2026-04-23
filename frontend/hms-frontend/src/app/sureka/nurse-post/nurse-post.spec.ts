import { ComponentFixture, TestBed } from '@angular/core/testing';

import {  CreateNurseComponent} from './nurse-post';

describe('NursePost', () => {
  let component: CreateNurseComponent;
  let fixture: ComponentFixture<CreateNurseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateNurseComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CreateNurseComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
