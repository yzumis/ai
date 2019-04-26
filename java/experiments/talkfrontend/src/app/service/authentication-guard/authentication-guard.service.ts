import { Injectable } from '@angular/core';
import { User } from './../../model/user/user';
import { Router, CanActivate } from '@angular/router';
import { AuthenticationService } from './../authentication/authentication.service';
import { LightNotificationService } from './../light-notification/light-notification.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuardService implements CanActivate {

  constructor(
    private authenticationService: AuthenticationService,
    private lightNotificationService: LightNotificationService,
    private router: Router
  ) {}

  canActivate(): boolean {
    if (!this.authenticationService.isAuthenticated()) {
      this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_AUTHENTICATION_GUARD_ERROR);
      this.router.navigate(['']);
      return false;
    }
    return true;
  }

}
