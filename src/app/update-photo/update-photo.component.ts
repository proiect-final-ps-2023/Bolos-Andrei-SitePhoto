import { Component } from '@angular/core';
import { Photo } from '../photo';
import { PhotoService } from '../photo.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-update-photo',
  templateUrl: './update-photo.component.html',
  styleUrls: ['./update-photo.component.css']
})
export class UpdatePhotoComponent {
  photo: Photo = new Photo();
  id!: number;
  constructor(private photoService: PhotoService, private router: Router, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.photoService.getPhotoById(this.id).subscribe(data => {
      this.photo = data;
    })
  }
  updatePhotoName() {
    this.photoService.updatePhotoName(this.id,this.photo).subscribe(data => {
      console.log(data);
      this.goToPhotoList();
    },error => console.log(error));
  }
  updatePhotoDescription() {
    this.photoService.updatePhotoDescription(this.id, this.photo).subscribe(data => {
      console.log(data);
      this.goToPhotoList();
    },error => console.log(error));
  }
  updatePhotoPath() {
    this.photoService.updatePhotoPath(this.id, this.photo).subscribe(data => {
      console.log(data);
      this.goToPhotoList();
    },error => console.log(error));
  }
  updatePhotoUser() {
    this.photoService.updatePhotoUser(this.id, this.photo).subscribe(data => {
      console.log(data);
      this.goToPhotoList();
    },error => console.log(error));
  }
  goToPhotoList() {
    this.router.navigate(['/photo']);
  }
  onSubmit() {
    this.updatePhotoName();
    this.updatePhotoDescription();
    this.updatePhotoPath();
    this.updatePhotoUser();
    this.goToPhotoList();
  }
}
