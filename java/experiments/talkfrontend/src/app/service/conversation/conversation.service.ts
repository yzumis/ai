import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpParams , HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, Subscriber, Subject } from 'rxjs'

import { Server } from './../../constants/server';
import { ConversationCreate } from './../../model/conversation/conversation-create';
import { AuthenticationService } from './../authentication/authentication.service';


@Injectable({
  providedIn: 'root'
})
export class ConversationService implements OnInit {

  private static readonly SAVE_PATH: string = "/conversations/create";

  private conversationChangedSubject: Subject<any>;

  constructor(private httpClient: HttpClient,
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
    this.conversationChangedSubject = new Subject<any>();
  }

  ngOnInit(): void {
  }

  save(conversationCreate: ConversationCreate) {
    console.log("save: "+ conversationCreate.idUser + " " + conversationCreate.idUserContact);
    this.httpClient.post(Server.SERVER_BASE_PATH + ConversationService.SAVE_PATH, conversationCreate).subscribe(
      data => {
        console.log("POST save request succeded");
        this.changeConversation();
        // this.router.navigate(['/mainScreen'], { queryParams: { refresh: 'true' } });
      },
      error => {
        console.log("POST save request failed");
      }
    );
  }

  getConversationChangedObservable(): Observable<any> {
    return this.conversationChangedSubject.asObservable();
  }

  changeConversation() {
    return this.conversationChangedSubject.next();
  }

}
