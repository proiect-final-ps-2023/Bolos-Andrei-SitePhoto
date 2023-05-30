import { Component, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { User } from '../user';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { webSocket } from 'rxjs/webSocket';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  user: User = new User();
  string: String = "";
  bool: Boolean = new Boolean();
  message = "";
  subject = webSocket('ws://localhost:8889/');
  constructor(private loginService: LoginService,
    private router: Router) { }
  ngOnInit(): void {

  }

  sendToServer($event: any) {
    this.subject.subscribe();
    this.subject.next(this.message);
    this.subject.complete();
  }
  login() {
    const x = this.loginService.login(this.string).subscribe(data => {
      this.bool = data;
    },
      error => console.log(error));
    return x;
  }
  getUser() {
    this.string = "?email=" + this.user.email;
    const x = this.loginService.findUserByEmail(this.string).subscribe(data => {
      this.user = data;
    },
      error => console.log(error));
  }
  onSubmit() {
    this.string = "?name=" + this.user.name + "&email=" + this.user.email + "&password=" + this.user.password;
    this.login()
    this.goToPhotoList();

  }
  goToPhotoList() {
    this.getUser();
    console.log(this.user);
    if (this.bool == true) {
      if (this.user.admin == true)
        this.router.navigate(['admin-page']);
      else
        this.router.navigate(['photo']);
    }
  }
  onRegister() {
    this.router.navigate(['register']);
  }
}
