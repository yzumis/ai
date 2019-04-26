import { Component, OnInit } from '@angular/core';
import { UserService } from './../service/user/user.service';
import { UserHasUserAsContactService } from './../service/user-contact/user-has-user-as-contact.service';
import { AuthenticationService } from './../service/authentication/authentication.service'
import { ConversationService } from './../service/conversation/conversation.service';
import { LightNotificationService } from './../service/light-notification/light-notification.service';

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
    private conversationService: ConversationService,
    private lightNotificationService: LightNotificationService
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
        this.userContactArray = userContactArray;
      },
      error => {
        this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_USER_CONTACTS_ERROR);
      }
    );
  }

  deleteContact(contactIduser: number) {
    var iduser = this.authenticationService.user.iduser;
    var userHasUserAsContact: UserHasUserAsContact = new UserHasUserAsContact(iduser, contactIduser);
    this.userHasUserAsContactService.deleteContact(userHasUserAsContact).subscribe(
      data => {
        this.userContacts(this.usernameFilter);
      },
      error => {
        this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_DELETE_CONTACT_ERROR);
      }
    );
  }

  saveContact(contactIduser: number) {
    var iduser = this.authenticationService.user.iduser;
    var userHasUserAsContact: UserHasUserAsContact = new UserHasUserAsContact(iduser, contactIduser);
    this.userHasUserAsContactService.saveContact(userHasUserAsContact).subscribe(
      data => {
        this.userContacts(this.usernameFilter);
      },
      error => {
        this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_SAVE_CONTACT_ERROR);
      }
    );
    
  }

  saveConversation(isContact: boolean, IdUserContact: number) {
    if(isContact) {
      var iduser = this.authenticationService.user.iduser;
      var conversationCreate: ConversationCreate = new ConversationCreate(iduser, IdUserContact);
      this.conversationService.save(conversationCreate);
    }
  }


}
