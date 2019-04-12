import { Injectable } from '@angular/core';
import { HttpClient, HttpParams , HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { Server } from './../../constants/server';
import { ConversationCreate } from './../../model/conversation/conversation-create';
import { AuthenticationService } from './../authentication/authentication.service';


@Injectable({
  providedIn: 'root'
})
export class ConversationService {

  private static readonly SAVE_PATH: string = "/conversations/create";

  constructor(private httpClient: HttpClient,
    private authenticationService: AuthenticationService,
    private router: Router
  ) { }

  save(conversationCreate: ConversationCreate) {
    console.log("save: "+ conversationCreate.idUser + " " + conversationCreate.idUserContact);
    this.httpClient.post(Server.SERVER_BASE_PATH + ConversationService.SAVE_PATH, conversationCreate).subscribe(
      data => {
        console.log("POST save request succeded");
        this.router.navigateByUrl('/mainScreen?refresh');
      },
      error => {
        console.log("POST save request failed");
      }
    );
  }

}
