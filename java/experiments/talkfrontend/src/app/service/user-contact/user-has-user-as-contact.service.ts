import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Server } from './../../constants/server';
import { UserHasUserAsContact } from './../../model/user-has-user-as-contact/user-has-user-as-contact';

import { AuthenticationService } from './../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class UserHasUserAsContactService {

  private static readonly SAVE_PATH: string = "/userhasuserascontact/save";
  private static readonly DELETE_PATH: string = "/userhasuserascontact/delete";

  constructor(
    private httpClient: HttpClient,
    private authenticationService: AuthenticationService
  ) { }

  saveContact(userHasUserAsContact: UserHasUserAsContact): Observable<any> {
    var httpHeaders: HttpHeaders = this.authenticationService.buildAuthenticationHttpHeaders();
    return this.httpClient.post(Server.SERVER_BASE_PATH + UserHasUserAsContactService.SAVE_PATH, userHasUserAsContact, { headers: httpHeaders });
  }

  deleteContact(userHasUserAsContact: UserHasUserAsContact): Observable<any> {
    var httpHeaders: HttpHeaders = this.authenticationService.buildAuthenticationHttpHeaders();
    return this.httpClient.post(Server.SERVER_BASE_PATH + UserHasUserAsContactService.DELETE_PATH, userHasUserAsContact, { headers: httpHeaders });
  }
  
}
