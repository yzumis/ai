import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

import { Server } from './../../constants/server';
import { UserHasUserAsContact } from './../../model/user-has-user-as-contact/user-has-user-as-contact';

@Injectable({
  providedIn: 'root'
})
export class UserHasUserAsContactService {

  private static readonly SAVE_PATH: string = "/userhasuserascontact/save";
  private static readonly DELETE_PATH: string = "/userhasuserascontact/delete";

  constructor(private httpClient: HttpClient) { }

  saveContact(userHasUserAsContact: UserHasUserAsContact): Observable<any> {
    console.log("saveContact: "+ userHasUserAsContact.user_iduser + " " + userHasUserAsContact.user_iduser_contact);
    return this.httpClient.post(Server.SERVER_BASE_PATH + UserHasUserAsContactService.SAVE_PATH, userHasUserAsContact);
  }

  deleteContact(userHasUserAsContact: UserHasUserAsContact): Observable<any> {
    console.log("deleteContact: "+ userHasUserAsContact.user_iduser + " " + userHasUserAsContact.user_iduser_contact);
    return this.httpClient.post(Server.SERVER_BASE_PATH + UserHasUserAsContactService.DELETE_PATH, userHasUserAsContact);
  }
  
}
