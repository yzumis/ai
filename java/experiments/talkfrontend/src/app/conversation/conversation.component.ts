import { Component, OnInit } from '@angular/core';

import { Observable } from 'rxjs';
import 'rxjs/add/observable/interval';

import { AuthenticationService } from './../service/authentication/authentication.service';
import { UserService } from './../service/user/user.service';
import { MessageService } from './../service/message/message.service';
import { LightNotificationService } from './../service/light-notification/light-notification.service';

import { Message } from './../model/message/message/message';
import { MessageCreate } from './../model/message/message-create/message-create';

@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.css']
})
export class ConversationComponent implements OnInit {

  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private messageService: MessageService,
    private lightNotificationService: LightNotificationService
  ) { }

  private readonly READ_MESSAGES_MILLISECONDS_INTERVAL: number = 1000;
  idUser:number;
  idConversation: number;
  messages: Message[];
  subscription: any;

  ngOnInit() {
    this.obtainUserMessages();
    this.createObtainUserMessagesScheduler(this.READ_MESSAGES_MILLISECONDS_INTERVAL);
  }

  obtainUserMessages() {
    this.idUser = this.authenticationService.user.iduser;
    this.userService.mainConversationByIdUser(this.idUser).subscribe(
      idConversation => {
        if(idConversation != null) {
          this.idConversation = idConversation;
          this.messageService.messagesByIdConversation(idConversation).subscribe(
            messages => {
              this.messages = messages;
            }
          )
        }
      },
      error => {
        this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_OBTAIN_MAIN_CONVERSATION_BY_ID_USER_ERROR);
      }
    )
  }

  createObtainUserMessagesScheduler(interval: number) {
    Observable.interval(interval) .subscribe(
      data =>
      {
        this.obtainUserMessages();
      },
      error =>
      {
        this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_OBTAIN_MESSAGES_ERROR);
      }
    );
  }

  saveMessage(text: string) {
    if(text !== "") {
      var messageCreate: MessageCreate = new MessageCreate(this.idConversation, this.idUser, text);
      this.messageService.save(messageCreate).subscribe(
        data => {
          this.obtainUserMessages();
        },
        error => {
          this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_SAVE_MESSAGE_ERROR);
        }
      )
    }
  }

}
