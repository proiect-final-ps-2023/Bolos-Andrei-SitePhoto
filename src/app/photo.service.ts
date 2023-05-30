import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Photo } from './photo';
@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  private baseURL = "http://localhost:8081/photo";
  private baseURL1 = "http://localhost:8081/photo/addPhoto";
  private baseURLUpdateName = 'http://localhost:8081/photo/updateName';
  private baseURLUpdateDescription = 'http://localhost:8081/photo/updateDescription';
  private baseURLUpdatePath = 'http://localhost:8081/photo/updatePath';
  private baseURLUpdateUser = 'http://localhost:8081/photo/updateUser';
  constructor(private httpClient: HttpClient) { }

  getPhotoList(): Observable<Photo[]> {
    return this.httpClient.get<Photo[]>(`${this.baseURL}/all`);
  }
  getFilteredPhotoList(threshold:number): Observable<Photo[]> {
    return this.httpClient.get<Photo[]>(`${this.baseURL}/findAllFiltered?threshold=${threshold}`);
  }
  addPhoto(photo: Photo, string: String): Observable<Object> {
    return this.httpClient.post(`${this.baseURL1}${string}`, photo);

  }
  getPhotoById(id: number): Observable<Photo> {
    return this.httpClient.get<Photo>(`${this.baseURL}/${id}`);
  }
  updatePhotoName(id: number,photo: Photo): Observable<Object> {
    return this.httpClient.post(`${this.baseURLUpdateName}?id=${id}&name=${photo.name}`, photo);
  }
  updatePhotoDescription(id: number, photo: Photo): Observable<Object> {
    return this.httpClient.post(`${this.baseURLUpdateDescription}?id=${id}&description=${photo.description}`, photo);
  }
  updatePhotoPath(id: number, photo: Photo): Observable<Object> {
    return this.httpClient.post(`${this.baseURLUpdatePath}?id=${id}&path=${photo.path}`, photo);
  }
  updatePhotoUser(id: number, photo: Photo): Observable<Object> {
    return this.httpClient.post(`${this.baseURLUpdateUser}?id=${id}&userid=${photo.userid}`, photo);
  }
  deletePhoto(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/delete/${id}`);
  }
  photoDetails() {

  }
}
