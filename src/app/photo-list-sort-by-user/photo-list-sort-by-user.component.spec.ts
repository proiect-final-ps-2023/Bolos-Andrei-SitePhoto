import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhotoListSortByUserComponent } from './photo-list-sort-by-user.component';

describe('PhotoListSortByUserComponent', () => {
  let component: PhotoListSortByUserComponent;
  let fixture: ComponentFixture<PhotoListSortByUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PhotoListSortByUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PhotoListSortByUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
