import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePhotoComponent } from './create-photo/create-photo.component';
import { PhotoListComponent } from './photo-list/photo-list.component';
import { UpdatePhotoComponent } from './update-photo/update-photo.component';
import { PhotoDetailsComponent } from './photo-details/photo-details.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { PhotoListSortByUserComponent } from './photo-list-sort-by-user/photo-list-sort-by-user.component';
import { PhotoListFilterByUserComponent } from './photo-list-filter-by-user/photo-list-filter-by-user.component';
import { LogoffComponent } from './logoff/logoff.component';

const routes: Routes = [
  { path: 'photo', component: PhotoListComponent },
  { path: 'create-photo', component: CreatePhotoComponent},
  { path: 'update-photo/:id', component: UpdatePhotoComponent },
  { path: 'photo-details/:id', component: PhotoDetailsComponent },
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  { path: 'admin-page', component: AdminPageComponent },
  { path: 'sort-user', component: PhotoListSortByUserComponent },
  { path: 'filter-user', component: PhotoListFilterByUserComponent },
  { path: "logoff", component: LogoffComponent },

  { path: '', redirectTo: 'login', pathMatch: 'full' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
