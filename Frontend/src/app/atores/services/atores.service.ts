import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs/operators';

import { Ator } from '../model/ator';

@Injectable({
  providedIn: 'root'
})
export class AtoresService {

  private readonly API = 'api/ator';

  constructor(private httpCliente: HttpClient) { }

  list() {
    return this.httpCliente.get<Ator[]>(this.API)
      .pipe(
        first(),
        delay(5000),
        tap(atores => console.log(atores))
      );
  }
}
