import { Component, OnInit } from '@angular/core';
import { UserService } from './../service/user/user.service';
import { UserRegister } from './../model/user/user-register';
import { UserLogin } from './../model/user/user-login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  register(username: string, password: string) {
    var userRegister: UserRegister = new UserRegister(username, password);
    this.userService.register(userRegister);
  }

  login(username: string, password: string) {
    var userLogin: UserLogin = new UserLogin(username, password);
    this.userService.login(userLogin);
  }

}
