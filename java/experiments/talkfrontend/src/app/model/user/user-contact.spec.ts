import { UserContact } from './user-contact';

describe('User', () => {
  it('should create an instance', () => {
    expect(new UserContact(1, "username", true)).toBeTruthy();
  });
});
