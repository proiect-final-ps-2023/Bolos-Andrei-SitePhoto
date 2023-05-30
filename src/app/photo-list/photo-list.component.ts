import { Component, OnInit, ElementRef,ViewChild } from '@angular/core';
import { Photo } from '../photo'
import { Router } from '@angular/router';
import { PhotoService } from '../photo.service';
import jsPDF from 'jspdf';

@Component({
  templateUrl: './photo-list.component.html',
  selector: 'app-photo-list',
  styleUrls: ['./photo-list.component.css']
})
export class PhotoListComponent {
  photoList: Photo[] | undefined;
  @ViewChild('content', { static: false }) element!: ElementRef;
  constructor(private photoService: PhotoService, private router:Router) { }

  ngOnInit(): void {
    this.getPhotos();
  }
  private getPhotos() {
    this.photoService.getPhotoList().subscribe(data => {
      //console.log(data);
      this.photoList = data;
    });
  }
  generatePDF() {
    let pdf = new jsPDF('l', 'px', 'a2', true);
    const options = {
      callback: (pdf: { save: (arg0: string) => void; }) => {
        pdf.save("PhotoList.pdf");
      }
    };
    pdf.html(this.element.nativeElement,options)
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
}
