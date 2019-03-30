import { UserRegister } from './user-register';

describe('UserRegister', () => {
  it('should create an instance', () => {
    expect(new UserRegister("username", "password")).toBeTruthy();
  });
});
