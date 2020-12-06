import { TestBed } from '@angular/core/testing';

import { PrivatePostService } from './private-post.service';

describe('PrivatePostService', () => {
  let service: PrivatePostService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrivatePostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
