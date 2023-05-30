import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhotoListFilterByUserComponent } from './photo-list-filter-by-user.component';

describe('PhotoListFilterByUserComponent', () => {
  let component: PhotoListFilterByUserComponent;
  let fixture: ComponentFixture<PhotoListFilterByUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PhotoListFilterByUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PhotoListFilterByUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
