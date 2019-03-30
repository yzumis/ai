import { Injectable } from '@angular/core';
import { User } from './../../model/user/user';
import { Router, CanActivate } from '@angular/router';
import { AuthenticationService } from './../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuardService implements CanActivate {

  constructor(private authenticationService: AuthenticationService,
    private router: Router) {}

  canActivate(): boolean {
    if (!this.authenticationService.isAuthenticated()) {
      this.router.navigate(['loginScreen']);
      return false;
    }
    return true;
  }

}
