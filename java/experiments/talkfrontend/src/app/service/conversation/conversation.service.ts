import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, Subscriber, Subject } from 'rxjs'

import { Server } from './../../constants/server';
import { ConversationCreate } from './../../model/conversation/conversation-create';
import { AuthenticationService } from './../authentication/authentication.service';
import { LightNotificationService } from './../light-notification/light-notification.service';


@Injectable({
  providedIn: 'root'
})
export class ConversationService implements OnInit {

  private static readonly SAVE_PATH: string = "/conversations/create";

  private conversationChangedSubject: Subject<any>;

  constructor(private httpClient: HttpClient,
    private authenticationService: AuthenticationService,
    private router: Router,
    private lightNotificationService: LightNotificationService
  ) {
    this.conversationChangedSubject = new Subject<any>();
  }

  ngOnInit(): void {
  }

  save(conversationCreate: ConversationCreate) {
    var httpHeaders: HttpHeaders = this.authenticationService.buildAuthenticationHttpHeaders();
    this.httpClient.post(Server.SERVER_BASE_PATH + ConversationService.SAVE_PATH, conversationCreate, { headers: httpHeaders }).subscribe(
      data => {
        this.changeConversation();
      },
      error => {
        this.lightNotificationService.addLightNotification(LightNotificationService.LIGHT_NOTIFICATION_CONVERSTION_CREATE_ERROR);
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
