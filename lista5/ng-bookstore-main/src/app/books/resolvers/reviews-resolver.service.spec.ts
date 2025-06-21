import { TestBed } from '@angular/core/testing';

import { ReviewsResolver } from './reviews-resolver.service';

describe('ReviewsResolverService', () => {
  let service: ReviewsResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReviewsResolver);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
