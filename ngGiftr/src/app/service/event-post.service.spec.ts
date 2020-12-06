import { TestBed } from '@angular/core/testing';

import { EventPostService } from './event-post.service';

describe('EventPostService', () => {
  let service: EventPostService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventPostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
