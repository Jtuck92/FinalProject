import { UserInEventPipe } from './user-in-event.pipe';

describe('UserInEventPipe', () => {
  it('create an instance', () => {
    const pipe = new UserInEventPipe();
    expect(pipe).toBeTruthy();
  });
});
