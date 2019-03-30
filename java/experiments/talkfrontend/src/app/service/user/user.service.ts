import { Injectable } from '@angular/core';
import { HttpClient, HttpParams , HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';

import { Server } from './../../constants/server';
import { UserRegister } from './../../model/user/user-register';
import { User } from './../../model/user/user';
import { UserLogin } from './../../model/user/user-login';
import { AuthenticationService } from './../authentication/authentication.service';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private static readonly REGISTER_PATH: string = "/users/register";
  private static readonly LOGIN_PATH: string = "/users/login";
  private static readonly USER_CONTACTS_PATH: string = "/users";

  constructor(private httpClient: HttpClient,
    private authenticationService: AuthenticationService,
    private router: Router
  ) { }

  register(userRegister: UserRegister) {
    console.log("register: "+ userRegister.username + " " + userRegister.password);
    this.httpClient.post(Server.SERVER_BASE_PATH + UserService.REGISTER_PATH, userRegister).subscribe(
      data => {
        console.log("POST register request succeded");
      },
      error => {
        console.log("POST register request failed");
      }
    );
  }

  login(userLogin: UserLogin) {
    console.log("login: "+ userLogin.username + " " + userLogin.password);
    this.httpClient.post(Server.SERVER_BASE_PATH + UserService.LOGIN_PATH, userLogin).subscribe(
      data => {
        this.authenticationService.user = new User(data['iduser'], data['username'], data['token']);        
        this.router.navigateByUrl('/mainScreen');
        console.log("POST login request succeded" + JSON.stringify(data));
      },
      error => {
        console.log("POST login request failed");
      }
    );
  }

  userContacts(iduser: number, usernameFilter: string) {
    console.log("userContacts: "+ iduser + " " + usernameFilter);
    var httpParams: HttpParams = new HttpParams()
    .append("iduser", String(iduser))
    .append("usernameFilter", usernameFilter);
    this.httpClient.get(Server.SERVER_BASE_PATH + UserService.USER_CONTACTS_PATH, { params: httpParams }).subscribe(
      data => {
        console.log("GET userContacts request succeded" +  + JSON.stringify(data));
      },
      error => {
        console.log("GET userContacts request failed");
      }
    );
  }

}
