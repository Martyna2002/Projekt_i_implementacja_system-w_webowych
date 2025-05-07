import { TestBed } from '@angular/core/testing';

import { ReviewsResolverService } from './reviews-resolver.service';

describe('ReviewsResolverService', () => {
  let service: ReviewsResolverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReviewsResolverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
