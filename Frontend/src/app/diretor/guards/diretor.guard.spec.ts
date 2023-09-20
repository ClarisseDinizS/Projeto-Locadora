import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { diretorGuard } from './diretor.guard';

describe('diretorGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => diretorGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
