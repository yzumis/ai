import { Component, OnInit } from '@angular/core';
import { UserService } from './../service/user/user.service';
import { UserHasUserAsContactService } from './../service/user-contact/user-has-user-as-contact.service';
import { AuthenticationService } from './../service/authentication/authentication.service'
import { ConversationService } from './../service/conversation/conversation.service';
import { UserContact } from './../model/user/user-contact';
import { UserHasUserAsContact } from './../model/user-has-user-as-contact/user-has-user-as-contact';
import { ConversationCreate } from './../model/conversation/conversation-create';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {
  usernameFilter: string;

  public userContactArray: UserContact[] = [];

  constructor(
    private userService: UserService,
    private authenticationService: AuthenticationService,
    private userHasUserAsContactService: UserHasUserAsContactService,
    private conversationService: ConversationService
  ) {
  }

  ngOnInit() {
    this.clearUserNameFilter();
  }

  clearUserNameFilter() {
    this.usernameFilter = "";
    this.userContacts(this.usernameFilter);
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

  deleteContact(contactIduser: number) {
    var iduser = this.authenticationService.user.iduser;
    var userHasUserAsContact: UserHasUserAsContact = new UserHasUserAsContact(iduser, contactIduser);
    this.userHasUserAsContactService.deleteContact(userHasUserAsContact).subscribe(
      data => {
        console.log("POST deleteContact request succeded");
        this.userContacts(this.usernameFilter);
      },
      error => {
        console.log("POST deleteContact request failed");
      }
    );
  }

  saveContact(contactIduser: number) {
    var iduser = this.authenticationService.user.iduser;
    var userHasUserAsContact: UserHasUserAsContact = new UserHasUserAsContact(iduser, contactIduser);
    this.userHasUserAsContactService.saveContact(userHasUserAsContact).subscribe(
      data => {
        console.log("POST saveContact request succeded");
        this.userContacts(this.usernameFilter);
      },
      error => {
        console.log("POST saveContact request failed");
      }
    );
    
  }

  saveConversation(isContact: boolean, IdUserContact: number) {
    if(isContact) {
      console.log("saveConversation");
      var iduser = this.authenticationService.user.iduser;
      var conversationCreate: ConversationCreate = new ConversationCreate(iduser, IdUserContact);
      this.conversationService.save(conversationCreate);
    }
  }


}
