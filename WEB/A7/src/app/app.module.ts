import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ViewGuestbooksComponent } from './view-guestbooks/view-guestbooks.component';
import { EditGuestbookComponent } from './edit-guestbook/edit-guestbook.component';
import { AddGuestbookComponent } from './add-guestbook/add-guestbook.component';
import { IonicModule } from '@ionic/angular';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ViewGuestbooksComponent,
    EditGuestbookComponent,
    AddGuestbookComponent,
    HomeComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, IonicModule.forRoot()],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
