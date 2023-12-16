import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {LoginComponent} from "./components/login/login.component";
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HeaderComponent} from "./components/header/header.component";
import {SignupComponent} from "./components/signup/signup.component";
import {authInterceptorProviders} from "./helpers/auth.interceptor";
import {xhrInterceptorProviders} from "./helpers/xhr.interceptor";
import {NotificationComponent} from "./components/notification/notification.component";
import { DashboardComponent } from './components/dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    SignupComponent,
    NotificationComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [authInterceptorProviders, xhrInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
