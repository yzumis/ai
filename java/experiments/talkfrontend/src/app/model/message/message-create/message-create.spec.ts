import { MessageCreate } from './message-create';

describe('MessageCreate', () => {
  it('should create an instance', () => {
    expect(new MessageCreate(1, 1, "a")).toBeTruthy();
  });
});
