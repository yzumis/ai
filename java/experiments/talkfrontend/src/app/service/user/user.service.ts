import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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

  constructor(private httpClient: HttpClient,
    private authenticationService: AuthenticationService,
    private router: Router
  ) { }

  register(userRegister: UserRegister) {
    console.log("register:"+ userRegister.username + userRegister.password);
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
    console.log("login:"+ userLogin.username + userLogin.password);
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

}
