import { Injectable } from '@angular/core';
import { User } from './../../model/user/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private _user: User;

  set user(user: User) {
    this._user = user;
  }

  get user(): User {
    return this._user;
  }

  isAuthenticated(): boolean {
    return this._user != null;
  }

  constructor() { }

}
