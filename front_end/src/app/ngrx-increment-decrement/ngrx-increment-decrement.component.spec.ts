import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NgrxIncrementDecrementComponent } from './ngrx-increment-decrement.component';

describe('NgrxIncrementDecrementComponent', () => {
  let component: NgrxIncrementDecrementComponent;
  let fixture: ComponentFixture<NgrxIncrementDecrementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NgrxIncrementDecrementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NgrxIncrementDecrementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
