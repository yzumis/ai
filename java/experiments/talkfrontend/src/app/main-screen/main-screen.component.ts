import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { UserService } from './../service/user/user.service';
import { AuthenticationService } from './../service/authentication/authentication.service';
import { ConversationService } from './../service/conversation/conversation.service';

@Component({
  selector: 'app-main-screen',
  templateUrl: './main-screen.component.html',
  styleUrls: ['./main-screen.component.css']
})
export class MainScreenComponent implements OnInit {

  selectedTab: number;
  conversationTabDisabled;

  constructor(
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private authenticationService: AuthenticationService,
    private conversationService: ConversationService
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
        } else {
          this.selectedTab = 1;
          this.conversationTabDisabled = true;
        }
      }
    )
  }

}
