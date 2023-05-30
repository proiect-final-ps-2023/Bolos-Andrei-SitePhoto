import { Component } from '@angular/core';
import { Photo } from '../photo';
import { PhotoService } from '../photo.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-create-photo',
  templateUrl: './create-photo.component.html',
  styleUrls: ['./create-photo.component.css']
})
export class CreatePhotoComponent {
  photo: Photo = new Photo();
  string: String = new String();
  selectedFile: any;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message!: string;
  //imageName: any;
  event!: any;
  constructor(private photoService: PhotoService, private router: Router,private httpClient:HttpClient) { }
  ngOnInit(): void {
    this.string = "";
    //this.photo.userid = 0;
  }
  savePhoto() {
    this.photoService.addPhoto(this.photo, this.string).subscribe(data => {
      console.log(data);
      this.goToPhotoList();
    },
      error => console.log(error));
  }
  goToPhotoList() {
    this.router.navigate(['/photo']);
  }
  onSubmit() {
    console.log(this.photo);
    this.string = "";
    this.string = "&name=" + this.photo.name + "&path=" + this.photo.path + "&description=" + this.photo.description + "&userid=" + this.photo.userid;
    console.log(this.string);
    this.savePhoto();
  }

  //public onFileChanged(event: { target: { files: File[]; }; }) {

  public onFileChanged(event: Event) {
    //Select File
    
    const file = (event.target as HTMLInputElement).files;
    this.selectedFile = file;
  }

  onUpload() {
    console.log(this.selectedFile);

    //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
    const uploadImageData = new FormData();
    uploadImageData.append('photo', this.selectedFile, this.selectedFile.name);

    //Make a call to the Spring Boot Application to save the image
    this.httpClient.post('http://localhost:8081/photo/addPhoto', uploadImageData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      });
  }

  getImage() {
    //Make a call to Sprinf Boot to get the Image Bytes.
    this.httpClient.get('http://localhost:8081/photo/name/' + this.photo.name).subscribe(
        res => {
          this.retrieveResonse = res;
          this.base64Data = this.retrieveResonse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        });
  }
}
