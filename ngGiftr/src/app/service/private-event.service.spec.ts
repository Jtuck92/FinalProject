import { TestBed } from '@angular/core/testing';

import { PrivateEventService } from './private-event.service';

describe('PrivateEventService', () => {
  let service: PrivateEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrivateEventService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
