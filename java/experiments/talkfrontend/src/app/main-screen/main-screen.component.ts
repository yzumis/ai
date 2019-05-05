import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { UserService } from './../service/user/user.service';
import { AuthenticationService } from './../service/authentication/authentication.service';
import { ConversationService } from './../service/conversation/conversation.service';
import { LightNotificationService } from './../service/light-notification/light-notification.service';

@Component({
  selector: 'app-main-screen',
  templateUrl: './main-screen.component.html',
  styleUrls: ['./main-screen.component.css']
})
export class MainScreenComponent implements OnInit {

  selectedTab: number;
  conversationTabDisabled: boolean;
  conversationTabUsername: string;

  constructor(
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private authenticationService: AuthenticationService,
    private conversationService: ConversationService,
    private lightNotificationService: LightNotificationService
  ) {
  }

  ngOnInit() {
    this.loadScreen();
    this.conversationService.getConversationChangedObservable().subscribe(
      () => {
        this.loadScreen();
      }
    );
  }

  private loadScreen() {
    var idUser = this.authenticationService.user.iduser;
    this.userService.mainConversationByIdUser(idUser).subscribe(
      idConversation => {
        if(idConversation !== null) {
          this.selectedTab = 0;
          this.conversationTabDisabled = false;
          this.conversationService.usernameByIdUserAndIdConversation(idUser, idConversation).subscribe(
            username => {
              this.conversationTabUsername = username;
            },
            error => {
              this.conversationTabUsername = null;
              this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_USERNAME_BY_ID_USER_AND_ID_CONVERSATION_ERROR);
            }
          );
        } else {
          this.selectedTab = 1;
          this.conversationTabDisabled = true;
          this.conversationTabUsername = null;
        }
      },
      error => {
        this.selectedTab = 1;
        this.conversationTabDisabled = true;
        this.conversationTabUsername = null;
        this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_OBTAIN_MAIN_CONVERSATION_BY_ID_USER_ERROR);
      }
    );
  }

}
