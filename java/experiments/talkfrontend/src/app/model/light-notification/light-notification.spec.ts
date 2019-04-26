import { LightNotification } from './light-notification';
import { LightNotificationLevel } from './level/light-notification-level';

describe('LightNotification', () => {
  it('should create an instance', () => {
    expect(new LightNotification("", LightNotificationLevel.SUCCESS)).toBeTruthy();
  });
});
