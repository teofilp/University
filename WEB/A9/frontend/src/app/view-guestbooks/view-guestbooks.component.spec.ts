import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewGuestbooksComponent } from './view-guestbooks.component';

describe('ViewGuestbooksComponent', () => {
  let component: ViewGuestbooksComponent;
  let fixture: ComponentFixture<ViewGuestbooksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewGuestbooksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewGuestbooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
