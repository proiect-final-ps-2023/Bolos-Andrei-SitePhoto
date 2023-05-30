import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

import { User } from '../user';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {
  userList: User[] = [];
  constructor(private loginService: LoginService,
    private router: Router) { }
  ngOnInit(): void {
    this.getUserlist();
  }
  getUserlist() {
    this.loginService.getUserList().subscribe(data => {
      this.userList = data;
    });
  }
  updateAdmin(id:number, user: User) {
    this.loginService.updateAdmin(id, user).subscribe(data => {
      this.getUserlist();
    });
 
  }
}
