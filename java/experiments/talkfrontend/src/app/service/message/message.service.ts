import { Injectable } from '@angular/core';
import { HttpClient, HttpParams , HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { Server } from './../../constants/server';
import { AuthenticationService } from './../authentication/authentication.service';
import { Message } from './../../model/message/message/message';
import { MessageCreate } from './../../model/message/message-create/message-create';


@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private static readonly SAVE_PATH: string = "/messages/save";
  private static readonly CONVERSATION_PATH: string = "/messages/byidconversation";

  constructor(private httpClient: HttpClient,
    private authenticationService: AuthenticationService,
    private router: Router
  ) { }

  save(messageCreate: MessageCreate): Observable<any> {
    var httpHeaders: HttpHeaders = this.authenticationService.buildAuthenticationHttpHeaders();
    return this.httpClient.post(Server.SERVER_BASE_PATH + MessageService.SAVE_PATH, messageCreate, { headers: httpHeaders });
  }

  messagesByIdConversation(idConversation: number): Observable<Message[]> {
    var httpHeaders: HttpHeaders = this.authenticationService.buildAuthenticationHttpHeaders();
    var httpParams: HttpParams = new HttpParams()
    .append("idConversation", String(idConversation))
    return this.httpClient.get<Message[]>(Server.SERVER_BASE_PATH + MessageService.CONVERSATION_PATH, { headers: httpHeaders, params: httpParams });
  }

}
