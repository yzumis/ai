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
    this.httpClient.post<User>(Server.SERVER_BASE_PATH + UserService.LOGIN_PATH, userLogin).subscribe(
      user  => {
        console.log("POST login request succeded " + JSON.stringify(user));
        this.authenticationService.user = user;        
        this.router.navigateByUrl('/mainScreen');
      },
      error => {
        console.log("POST login request failed");
      }
    );
  }

  userContacts(iduser: number, usernameFilter: string): Observable<UserContact[]> {
    console.log("userContacts: "+ iduser + " " + usernameFilter);
    var httpParams: HttpParams = new HttpParams()
    .append("iduser", String(iduser))
    .append("usernameFilter", usernameFilter);
    return this.httpClient.get<UserContact[]>(Server.SERVER_BASE_PATH + UserService.USER_CONTACTS_PATH, { params: httpParams });
  }

  mainConversationByIdUser(idUser: number): Observable<number> {
    console.log("mainConversationByIdUser: "+ idUser);
    var httpParams: HttpParams = new HttpParams()
    .append("idUser", String(idUser));
    return this.httpClient.get<number>(Server.SERVER_BASE_PATH + UserService.MAIN_CONVERSATION_BY_ID_USER_PATH, { params: httpParams });
  }

}