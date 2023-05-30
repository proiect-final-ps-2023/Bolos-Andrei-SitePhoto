import { Component,OnInit} from '@angular/core';
import { Form, FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { User } from "../user";
import { LoginService } from "../login.service";
import { ActivatedRoute, Router } from "@angular/router";
import { EmailSenderService } from "../email-sender.service";
@Component({
  selector: 'app-email-sender',
  templateUrl: './email-sender.component.html',
  styleUrls: ['./email-sender.component.css']
})
export class EmailSenderComponent implements OnInit{
  FormData: FormGroup | undefined;
  EmailAddress: string = '';
  Body: string = '';

  constructor(private emailService: EmailSenderService,
    private builder: FormBuilder,
    private route: ActivatedRoute,
    public router: Router) { }

  ngOnInit(): void {
    this.FormData = this.builder.group({
      EmailAddress: [''],
      Body: [''],
    })
  }

  onSubmit(form: FormGroup) {
    const input = {
      to: form.value.EmailAddress,
      subject: 'Email from Angular app',
      html: form.value.Body
    };

    this.emailService.SendEmail(input)
      .subscribe(() => {
        console.log("success");
        location.href = 'https://mailthis.to/confirm';

      })
  }
}
