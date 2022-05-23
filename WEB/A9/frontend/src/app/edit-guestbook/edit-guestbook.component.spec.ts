import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditGuestbookComponent } from './edit-guestbook.component';

describe('EditGuestbookComponent', () => {
  let component: EditGuestbookComponent;
  let fixture: ComponentFixture<EditGuestbookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditGuestbookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditGuestbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
