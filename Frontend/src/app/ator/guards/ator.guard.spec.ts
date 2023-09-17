import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { atorGuard } from './ator.guard';

describe('atorGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => atorGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
