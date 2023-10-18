import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs/operators';

import { Titulo } from '../model/titulo';

@Injectable({
  providedIn: 'root'
})
export class TituloService {

  private readonly API = 'api/titulo';

  constructor(private httpCliente: HttpClient) { }

  listar() {
    return this.httpCliente.get<Titulo[]>(this.API)
      .pipe(
        first()
        //delay(5000),
        // tap(titulo => console.log(titulo))
      );
  }

  buscarPorId(id: number) {
    return this.httpCliente.get<Titulo>(`${this.API}/${id}`);
  }

  salvar(registro: Partial<Titulo>) {
    if (registro.id != 0) {
      return this.atualizar(registro);
    }
    return this.criar(registro);
  }

  private criar(registro: Partial<Titulo>) {
    return this.httpCliente.post<Titulo>(this.API, registro).pipe(first());
  }

  private atualizar(registro: Partial<Titulo>) {
    return this.httpCliente
      .put<Titulo>(`${this.API}/${registro.id}`, registro)
      .pipe(first());
  }

  excluir(id: number) {
    return this.httpCliente.delete(`${this.API}/${id}`).pipe(first());
  }
}
