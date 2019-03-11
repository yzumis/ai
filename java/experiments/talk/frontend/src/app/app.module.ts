import { BrowserModule } from '@angular/platform-browser';
import { NgModule} from '@angular/core';

import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule, MatFormFieldModule, MatInputModule, MatRippleModule, MatListModule, MatTabsModule, MatCardModule, MatBadgeModule} from '@angular/material';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LoginScreenComponent } from './login-screen/login-screen.component';
import { MainScreenComponent } from './main-screen/main-screen.component';
import { AppRoutingModule } from './/app-routing.module';
import { ConversationComponent } from './conversation/conversation.component';
import { ContactsComponent } from './contacts/contacts.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LoginScreenComponent,
    MainScreenComponent,
    ConversationComponent,
    ContactsComponent
  ],
  imports: [
    BrowserModule,
    NoopAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatInputModule,
    MatRippleModule,
    MatListModule,
    MatTabsModule,
    MatCardModule,
    MatBadgeModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
