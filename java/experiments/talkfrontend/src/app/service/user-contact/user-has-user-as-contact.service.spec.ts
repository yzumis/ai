import { TestBed } from '@angular/core/testing';

import { UserHasUserAsContactService } from './user-has-user-as-contact.service';

describe('UserHasUserAsContactService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserHasUserAsContactService = TestBed.get(UserHasUserAsContactService);
    expect(service).toBeTruthy();
  });
});
