import { Component, OnInit } from '@angular/core';
import { UserService } from './../service/user/user.service';
import { AuthenticationService } from './../service/authentication/authentication.service'

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {
  usernameFilter: string;

  constructor(private userService: UserService, private authenticationService: AuthenticationService) {
    this.usernameFilter = "";
  }

  ngOnInit() {
    var iduser = this.authenticationService.user.iduser;
    this.userService.userContacts(iduser, "");
  }

  userContacts(usernameFilter: string) {
    var iduser = this.authenticationService.user.iduser;
    this.userService.userContacts(iduser, usernameFilter);
  }

}
