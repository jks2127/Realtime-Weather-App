import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeaklyComponent } from './weakly.component';

describe('WeaklyComponent', () => {
  let component: WeaklyComponent;
  let fixture: ComponentFixture<WeaklyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeaklyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeaklyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
