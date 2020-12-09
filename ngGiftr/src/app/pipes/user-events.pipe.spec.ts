import { UserEventsPipe } from './user-events.pipe';

describe('UserEventsPipe', () => {
  it('create an instance', () => {
    const pipe = new UserEventsPipe();
    expect(pipe).toBeTruthy();
  });
});
