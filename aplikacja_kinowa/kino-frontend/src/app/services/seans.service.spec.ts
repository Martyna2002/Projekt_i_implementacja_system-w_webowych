import { TestBed } from '@angular/core/testing';

import { SeansService } from './seans.service';

describe('SeansService', () => {
  let service: SeansService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SeansService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
