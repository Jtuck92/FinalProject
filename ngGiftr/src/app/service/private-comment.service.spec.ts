import { TestBed } from '@angular/core/testing';

import { PrivateCommentService } from './private-comment.service';

describe('PrivateCommentService', () => {
  let service: PrivateCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrivateCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
