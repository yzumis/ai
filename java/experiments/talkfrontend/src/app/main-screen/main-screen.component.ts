import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { UserService } from './../service/user/user.service';

@Component({
  selector: 'app-main-screen',
  templateUrl: './main-screen.component.html',
  styleUrls: ['./main-screen.component.css']
})
export class MainScreenComponent implements OnInit {

  selectedTab: number;

  constructor(
    private activatedRoute: ActivatedRoute,
    private userService: UserService
  ) { }

  ngOnInit() {
    var idUser: number;
    this.userService.mainConversationByIdUser(idUser).subscribe(
      idConversation => {
        if(typeof idConversation !== 'undefined') {
          this.selectedTab = 0;
        } else {
          this.selectedTab = 1;
        }
      }
    )
  }

}
