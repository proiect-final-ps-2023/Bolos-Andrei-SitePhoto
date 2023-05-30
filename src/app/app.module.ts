import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PhotoListComponent } from './photo-list/photo-list.component';
import { CreatePhotoComponent } from './create-photo/create-photo.component';
import {FormsModule} from '@angular/forms';
import { UpdatePhotoComponent } from './update-photo/update-photo.component';
import { PhotoDetailsComponent } from './photo-details/photo-details.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { PhotoListSortByUserComponent } from './photo-list-sort-by-user/photo-list-sort-by-user.component';
import { PhotoListFilterByUserComponent } from './photo-list-filter-by-user/photo-list-filter-by-user.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { NgxCaptchaModule } from 'ngx-captcha';
import { EmailSenderComponent } from './email-sender/email-sender.component';
import { LogoffComponent } from './logoff/logoff.component';
@NgModule({
  declarations: [
    AppComponent,
    PhotoListComponent,
    CreatePhotoComponent,
    UpdatePhotoComponent,
    PhotoDetailsComponent,
    LoginComponent,
    RegisterComponent,
    PhotoListSortByUserComponent,
    PhotoListFilterByUserComponent,
    AdminPageComponent,
    EmailSenderComponent,
    LogoffComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxCaptchaModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
