import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AppRoutingModule } from './app-routing-module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddUserComponent } from './user/add-user/add-user.component';
import { ListeUserComponent } from './user/liste-user/liste-user.component';
import { UpdateUserComponent } from './user/update-user/update-user.component';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    AddUserComponent,
    ListeUserComponent,
    UpdateUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
