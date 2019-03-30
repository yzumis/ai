import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginScreenComponent }      from './login-screen/login-screen.component';
import { MainScreenComponent }      from './main-screen/main-screen.component';
import { AuthenticationGuardService as AuthGuard } from './service/authentication-guard/authentication-guard.service';

const routes: Routes = [
  { path: 'loginScreen', component: LoginScreenComponent },
  { path: 'mainScreen', component: MainScreenComponent, canActivate: [AuthGuard] }
];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ]
})
export class AppRoutingModule {}