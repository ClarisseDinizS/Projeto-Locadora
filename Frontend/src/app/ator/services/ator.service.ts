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
        first()
        //delay(5000),
        // tap(ator => console.log(ator))
      );
  }

  buscarPorId(id: number) {
    return this.httpCliente.get<Ator>(`${this.API}/${id}`);
  }

  salvar(registro: Partial<Ator>) {
    if (registro.id != 0) {
      return this.atualizar(registro);
    }
    return this.criar(registro);
  }

  private criar(registro: Partial<Ator>) {
    return this.httpCliente.post<Ator>(this.API, registro).pipe(first());
  }

  private atualizar(registro: Partial<Ator>) {
    return this.httpCliente
      .put<Ator>(`${this.API}/${registro.id}`, registro)
      .pipe(first());
  }

  excluir(id: number) {
    return this.httpCliente.delete(`${this.API}/${id}`, { observe: 'response' }).pipe(first());
  }
}
