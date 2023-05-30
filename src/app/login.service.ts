import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private baseURL = 'http://localhost:8081/user'

  constructor(private httpClient: HttpClient) {

  }
  login(string: String): Observable<Boolean> {
    return this.httpClient.get<Boolean>(`${this.baseURL}/login${string}`);
  }
  logoff(string: String): Observable<Boolean> {
    return this.httpClient.get<Boolean>(`${this.baseURL}/logoff${string}`);
  }
  findUserByEmail(string: String): Observable<User> {
    return this.httpClient.get<User>(`${this.baseURL}/findUserByEmail${string}`);
  }
  register(string: String, user: User): Observable<Object> {
    return this.httpClient.put<Boolean>(`${this.baseURL}/register${string}`, user);
  }
  getUserList(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.baseURL}/all`);
  }
  updateAdmin(id: number, user: User): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}/updateAdmin?id=${id}`, user);
  }
}
