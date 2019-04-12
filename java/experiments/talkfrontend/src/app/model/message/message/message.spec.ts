import { Message } from './message';

describe('Message', () => {
  it('should create an instance', () => {
    expect(new Message(1, 1, 1, "a", 1)).toBeTruthy();
  });
});
