import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-logoff',
  templateUrl: './logoff.component.html',
  styleUrls: ['./logoff.component.css']
})
export class LogoffComponent implements OnInit{
  user: User = new User();
  string: String = "";
  bool: Boolean = new Boolean();

  constructor(private loginService: LoginService,
    private router: Router) { }

  ngOnInit(): void {

  }
  getUser() {
    this.string = "?email=" + this.user.email;
    const x = this.loginService.findUserByEmail(this.string).subscribe(data => {
      this.user = data;
    },
      error => console.log(error));
  }
  logoff() {
    const x = this.loginService.logoff(this.string).subscribe(data => {
      this.bool = data;
    },
      error => console.log(error));
    return x;
  }
  onLogoff() {
    this.logoff();
    this.router.navigate(['login']);
  }
}
