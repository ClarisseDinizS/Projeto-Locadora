import { TestBed } from '@angular/core/testing';

import { AtoresService } from './atores.service';

describe('AtoresService', () => {
  let service: AtoresService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AtoresService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
