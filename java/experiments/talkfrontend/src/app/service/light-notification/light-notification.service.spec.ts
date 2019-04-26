import { TestBed } from '@angular/core/testing';

import { LightNotificationService } from './light-notification.service';

describe('ConversationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LightNotificationService = TestBed.get(LightNotificationService);
    expect(service).toBeTruthy();
  });
});
