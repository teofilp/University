import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddGuestbookComponent } from './add-guestbook/add-guestbook.component';
import { EditGuestbookComponent } from './edit-guestbook/edit-guestbook.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ViewGuestbooksComponent } from './view-guestbooks/view-guestbooks.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'edit/:id',
    component: EditGuestbookComponent,
  },
  {
    path: 'add',
    component: AddGuestbookComponent,
  },
  {
    path: 'view',
    component: ViewGuestbooksComponent,
  },
  {
    path: '',
    component: HomeComponent,
  },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
