import { Injectable } from '@angular/core';
import { HttpClient, HttpParams , HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { Server } from './../../constants/server';
import { UserRegister } from './../../model/user/user-register';
import { User } from './../../model/user/user';
import { UserLogin } from './../../model/user/user-login';
import { UserContact } from './../../model/user/user-contact';
import { AuthenticationService } from './../authentication/authentication.service';
import { LightNotificationService } from './../light-notification/light-notification.service';
import { LightNotification } from './../../model/light-notification/light-notification';
import { LightNotificationLevel } from './../../model/light-notification/level/light-notification-level';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private static readonly REGISTER_PATH: string = "/users/register";
  private static readonly LOGIN_PATH: string = "/users/login";
  private static readonly USER_CONTACTS_PATH: string = "/users";
  private static readonly MAIN_CONVERSATION_BY_ID_USER_PATH: string = "/users/mainconversationbyiduser";

  constructor(private httpClient: HttpClient,
    private authenticationService: AuthenticationService,
    private router: Router,
    private lightNotificationService: LightNotificationService,
  ) { }

  register(userRegister: UserRegister) {
    this.httpClient.post(Server.SERVER_BASE_PATH + UserService.REGISTER_PATH, userRegister).subscribe(
      data => {
        this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_REGISTER_OK);
      },
      error => {
        this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_REGISTER_ERROR);
      }
    );
  }

  login(userLogin: UserLogin) {
    this.httpClient.post<User>(Server.SERVER_BASE_PATH + UserService.LOGIN_PATH, userLogin).subscribe(
      user  => {
        this.authenticationService.user = user;        
        this.router.navigateByUrl('/mainScreen');
      },
      error => {
        this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_LOGIN_ERROR);
      }
    );
  }

  userContacts(iduser: number, usernameFilter: string): Observable<UserContact[]> {
    var httpHeaders: HttpHeaders = this.authenticationService.buildAuthenticationHttpHeaders();
    var httpParams: HttpParams = new HttpParams()
    .append("iduser", String(iduser))
    .append("usernameFilter", usernameFilter);
    return this.httpClient.get<UserContact[]>(Server.SERVER_BASE_PATH + UserService.USER_CONTACTS_PATH, { headers: httpHeaders, params: httpParams });
  }

  mainConversationByIdUser(idUser: number): Observable<number> {
    var httpHeaders: HttpHeaders = this.authenticationService.buildAuthenticationHttpHeaders();
    var httpParams: HttpParams = new HttpParams()
    .append("idUser", String(idUser));
    return this.httpClient.get<number>(Server.SERVER_BASE_PATH + UserService.MAIN_CONVERSATION_BY_ID_USER_PATH, { headers: httpHeaders, params: httpParams });
  }

}
