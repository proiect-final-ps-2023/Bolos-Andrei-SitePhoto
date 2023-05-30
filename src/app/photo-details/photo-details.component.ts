import { Component, ElementRef, ViewChild } from '@angular/core';
import { Photo } from '../photo';
import { PhotoService } from '../photo.service';
import { ActivatedRoute, Router } from '@angular/router';
import * as JSonToXML from 'js2xmlparser';
import jsPDF from 'jspdf';

@Component({
  selector: 'app-photo-details',
  templateUrl: './photo-details.component.html',
  styleUrls: ['./photo-details.component.css']
})
export class PhotoDetailsComponent {
  id!: number;
  photo!: Photo;
  @ViewChild('content', { static: false }) element!: ElementRef;

  constructor(private photoService: PhotoService, private router: Router, private route: ActivatedRoute) { }
  generatePDF() {
    let pdf = new jsPDF('p', 'px', 'a1', false);
    const options = {
      callback: (pdf: { save: (arg0: string) => void; }) => {
        pdf.save("Detailed Photo.pdf");
      }
    };
    pdf.html(this.element.nativeElement, options)
  }
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.photo = new Photo();
    let pdf = new jsPDF('p', 'px', 'a1', false);
    this.photoService.getPhotoById(this.id).subscribe(data => {
      this.photo = data;
      console.log(JSonToXML.parse("photo", this.photo));
      //pdf.text(JSonToXML.parse("photo", this.photo).toString());
    })
  }
}
