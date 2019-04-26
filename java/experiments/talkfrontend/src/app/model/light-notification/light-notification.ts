import { LightNotificationLevel } from './level/light-notification-level';

export class LightNotification {
    text: string;
    lightNotificationLevel: LightNotificationLevel;

    constructor(
        text: string,
        lightNotificationLevel: LightNotificationLevel
    ) {
        this.text = text;
        this.lightNotificationLevel = lightNotificationLevel;
    }

}
