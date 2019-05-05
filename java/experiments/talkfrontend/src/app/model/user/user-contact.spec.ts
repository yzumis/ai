import { UserContact } from './user-contact';

describe('UserContact', () => {
  it('should create an instance', () => {
    expect(new UserContact(1, "username", true, true)).toBeTruthy();
  });
});
