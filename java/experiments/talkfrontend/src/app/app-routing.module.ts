import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginScreenComponent }      from './login-screen/login-screen.component';
import { MainScreenComponent }      from './main-screen/main-screen.component';

const routes: Routes = [
  { path: 'loginScreen', component: LoginScreenComponent },
  { path: 'mainScreen', component: MainScreenComponent }
];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ]
})
export class AppRoutingModule {}