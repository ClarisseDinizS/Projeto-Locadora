import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs/operators';

import { Ator } from '../model/ator';

@Injectable({
  providedIn: 'root'
})
export class AtorService {

  private readonly API = 'api/ator';

  constructor(private httpCliente: HttpClient) { }

  listar() {
    return this.httpCliente.get<Ator[]>(this.API)
      .pipe(
        first(),
        //delay(5000),
        tap(ator => console.log(ator))
      );
  }

  salvar(registro: Partial<Ator>) {
    return this.httpCliente.post<Ator>(this.API, registro).pipe(first());
  }
}
