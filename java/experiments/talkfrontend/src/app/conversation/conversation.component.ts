import { Component, OnInit } from '@angular/core';

import { AuthenticationService } from './../service/authentication/authentication.service';
import { UserService } from './../service/user/user.service';
import { MessageService } from './../service/message/message.service';
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
    private messageService: MessageService
  ) { }

  idUser:number;
  idConversation: number;
  messages: Message[];

  ngOnInit() {
    this.obtainUserMessages();
  }

  obtainUserMessages() {
    this.idUser = this.authenticationService.user.iduser;
    this.userService.mainConversationByIdUser(this.idUser).subscribe(
      idConversation => {
        this.idConversation = idConversation;
        this.messageService.messagesByIdConversation(idConversation).subscribe(
          messages => {
            this.messages = messages;
          }
        )
      }
    )
  }

  saveMessage(text: string) {
    var messageCreate: MessageCreate = new MessageCreate(this.idConversation, this.idUser, text);
    this.messageService.save(messageCreate).subscribe(
      data => {
        this.obtainUserMessages();
      }
    )
  }

}
