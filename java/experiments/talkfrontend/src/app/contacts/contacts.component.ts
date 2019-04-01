import { Component, OnInit } from '@angular/core';
import { UserService } from './../service/user/user.service';
import { AuthenticationService } from './../service/authentication/authentication.service'
import { UserContact } from './../model/user/user-contact';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {
  usernameFilter: string;

  public userContactArray: UserContact[] = [];

  constructor(private userService: UserService, private authenticationService: AuthenticationService) {
    this.usernameFilter = "";
  }

  ngOnInit() {
    this.userContacts("");
  }

  userContacts(usernameFilter: string) {
    var iduser = this.authenticationService.user.iduser;
    this.userService.userContacts(iduser, usernameFilter).subscribe(
      userContactArray => {
        console.log("GET userContacts request succeded " +  + JSON.stringify(userContactArray));
        this.userContactArray = userContactArray;
      },
      error => {
        console.log("GET userContacts request failed");
      }
    );
  }


}
