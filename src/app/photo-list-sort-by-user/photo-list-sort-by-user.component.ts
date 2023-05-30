import { Component } from '@angular/core';
import { Photo } from '../photo'
import { Router } from '@angular/router';
import { PhotoService } from '../photo.service';

@Component({
  selector: 'app-photo-list-sort-by-user',
  templateUrl: './photo-list-sort-by-user.component.html',
  styleUrls: ['./photo-list-sort-by-user.component.css']
})
export class PhotoListSortByUserComponent {
  photoList!: Photo[];
  constructor(private photoService: PhotoService, private router: Router) { }

  ngOnInit(): void {
    this.getPhotos();
  }
  private getPhotos() {
    this.photoService.getPhotoList().subscribe(data => {
      //console.log(data);
      this.photoList = data;
    });
  }
  updatePhoto(id: number) {
    this.router.navigate(['update-photo', id]);
  }
  deletePhoto(id: number) {
    this.photoService.deletePhoto(id).subscribe(data => {
      console.log(data);
      this.getPhotos();
    });
  }
  photoDetails(id: number) {
    this.router.navigate(['photo-details', id]);
  }

  toggleSort(): void {
    this.photoList.sort((a, b) => {
      if (a.id < b.id) {
        this.getPhotos();

        return 1;
      }
      if (a.id > b.id) {
        return -1;
      }
      return 0;
    });

  }
}
