import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MatSnackBar, MatSnackBarConfig, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition} from '@angular/material';
import { Observable, Subscriber, Subject } from 'rxjs';

import { LightNotification } from './../../model/light-notification/light-notification';
import { LightNotificationLevel } from './../../model/light-notification/level/light-notification-level';

@Injectable({
  providedIn: 'root'
})
export class LightNotificationService implements OnInit {

    public static readonly LIGHT_NOTIFICATION_REGISTER_ERROR: LightNotification = new LightNotification("User already registered", LightNotificationLevel.ERROR);
    public static readonly LIGHT_NOTIFICATION_REGISTER_OK: LightNotification = new LightNotification("User properly registered", LightNotificationLevel.SUCCESS);
    public static readonly LIGHT_NOTIFICATION_LOGIN_ERROR: LightNotification = new LightNotification("Login error", LightNotificationLevel.ERROR);
    public static readonly LIGHT_NOTIFICATION_SAVE_MESSAGE_ERROR: LightNotification = new LightNotification("Saving message error", LightNotificationLevel.ERROR);
    public static readonly LIGHT_NOTIFICATION_OBTAIN_MESSAGES_ERROR: LightNotification = new LightNotification("Obtain messages error", LightNotificationLevel.ERROR);
    public static readonly LIGHT_NOTIFICATION_CONVERSTION_CREATE_ERROR: LightNotification = new LightNotification("Create conversation error", LightNotificationLevel.ERROR);
    public static readonly LIGHT_NOTIFICATION_OBTAIN_MAIN_CONVERSATION_BY_ID_USER_ERROR: LightNotification = new LightNotification("Obtain main conversation error", LightNotificationLevel.ERROR);
    public static readonly LIGHT_NOTIFICATION_AUTHENTICATION_GUARD_ERROR: LightNotification = new LightNotification("Not authenticated", LightNotificationLevel.ERROR);
    public static readonly LIGHT_NOTIFICATION_USER_CONTACTS_ERROR: LightNotification = new LightNotification("Obtain user contacts error", LightNotificationLevel.ERROR);
    public static readonly LIGHT_NOTIFICATION_DELETE_CONTACT_ERROR: LightNotification = new LightNotification("Delete contact error", LightNotificationLevel.ERROR);    
    public static readonly LIGHT_NOTIFICATION_SAVE_CONTACT_ERROR: LightNotification = new LightNotification("Save contact error", LightNotificationLevel.ERROR);    
    
    private static readonly MAT_SNACK_ACTION_TEXT: string = 'Close';
    private static readonly MAT_SNACK_BAR_HORIZONTAL_POSITION: MatSnackBarHorizontalPosition = 'right';
    private static readonly MAT_SNACK_BAR_VERTICAL_POSITION: MatSnackBarVerticalPosition = 'top';
    private static readonly MAT_SNACK_BAR_DURATION_MILLISECONDS: number = 2000;
    private readonly matSnackBarConfig: MatSnackBarConfig;

    constructor(
        private router: Router,
        private snackBar: MatSnackBar
    ) {
        this.matSnackBarConfig = new MatSnackBarConfig();
        this.matSnackBarConfig.verticalPosition = LightNotificationService.MAT_SNACK_BAR_VERTICAL_POSITION;
        this.matSnackBarConfig.horizontalPosition = LightNotificationService.MAT_SNACK_BAR_HORIZONTAL_POSITION;
        this.matSnackBarConfig.duration = LightNotificationService.MAT_SNACK_BAR_DURATION_MILLISECONDS;
    }
    
    ngOnInit(): void {
    }
    
    public addLightNotification(lightNotification: LightNotification) {
        this.snackBar.open(
            lightNotification.text,
            LightNotificationService.MAT_SNACK_ACTION_TEXT,
            this.matSnackBarConfig
        );
    }
    
}
