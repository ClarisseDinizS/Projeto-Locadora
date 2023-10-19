import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { itemGuard } from './item.guard';

describe('atorGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) =>
      TestBed.runInInjectionContext(() => itemGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
