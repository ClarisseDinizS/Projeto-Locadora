import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { tituloGuard } from './titulo.guard';

describe('atorGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) =>
      TestBed.runInInjectionContext(() => tituloGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
