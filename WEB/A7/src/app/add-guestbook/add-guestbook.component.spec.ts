import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGuestbookComponent } from './add-guestbook.component';

describe('AddGuestbookComponent', () => {
  let component: AddGuestbookComponent;
  let fixture: ComponentFixture<AddGuestbookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddGuestbookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddGuestbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
