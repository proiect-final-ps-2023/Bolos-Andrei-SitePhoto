import { Component } from '@angular/core';
import { Photo } from '../photo'
import { Router } from '@angular/router';
import { PhotoService } from '../photo.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-photo-list-filter-by-user',
  templateUrl: './photo-list-filter-by-user.component.html',
  styleUrls: ['./photo-list-filter-by-user.component.css']
})
export class PhotoListFilterByUserComponent {
  filterText = '';
  threshold!: number;
  photoList!: Photo[];
  photoFilteredList!: Photo[];
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

  putFilter(threshold:number){
    this.photoService.getFilteredPhotoList(this.threshold).subscribe(data => {
      //console.log(data);
      this.photoList = data;

    });  }
}
