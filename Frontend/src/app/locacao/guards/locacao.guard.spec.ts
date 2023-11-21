import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { locacaoGuard } from './locacao.guard';

describe('guardsGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) =>
    TestBed.runInInjectionContext(() => locacaoGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
