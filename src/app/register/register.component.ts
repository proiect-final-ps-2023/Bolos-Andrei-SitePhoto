import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { LoginComponent } from '../login/login.component';
import { LoginService } from '../login.service';
import { Route, Router } from '@angular/router';
import { EmailSenderService } from "../email-sender.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  user: User = new User();
  string: String = "";
  siteKey: string | undefined;
  constructor(private loginService: LoginService, private emailService: EmailSenderService,
    private router: Router) { }
  ngOnInit(): void {
    this.siteKey = "6Ld9BuAlAAAAAHn_6kXn0azYF4DuITpZsTQO09mB";
  }
  register() {
    this.loginService.register(this.string, this.user).subscribe(data => {
      console.log(data);
    },
      error => console.log(error));
    //const input = {
    //  to: this.user.email,
    //  subject: 'Email from Angular app',
    //  html: 'You have successfully registered!'
    //};
    //this.emailService.SendEmail(input)
    //  .subscribe(() => {
    //    console.log("success");
    //    //location.href = 'https://mailthis.to/confirm';
    //  })
  }
  onSubmit() {
    this.string = "?name=" + this.user.name+ "&email=" + this.user.email+ "&password=" + this.user.password;
    //console.log(this.user);
    //console.log(this.string);
    this.register();
    this.router.navigate(['login']);
  }
}
