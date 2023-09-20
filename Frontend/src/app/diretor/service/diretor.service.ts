import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs/operators';

import { Diretor } from '../model/diretor';

@Injectable({
  providedIn: 'root'
})
export class DiretorService {

  private readonly API = 'api/diretor';

  constructor(private httpCliente: HttpClient) { }

  listar() {
    return this.httpCliente.get<Diretor[]>(this.API)
      .pipe(
        first()
      );
  }

  buscarPorId(id: number) {
    return this.httpCliente.get<Diretor>(`${this.API}/${id}`);
  }

  salvar(registro: Partial<Diretor>) {
    if (registro.id != 0) {
      return this.atualizar(registro);
    }
    return this.criar(registro);
  }

  private criar(registro: Partial<Diretor>) {
    return this.httpCliente.post<Diretor>(this.API, registro).pipe(first());
  }

  private atualizar(registro: Partial<Diretor>) {
    return this.httpCliente
      .put<Diretor>(`${this.API}/${registro.id}`, registro)
      .pipe(first());
  }

  excluir(id: number) {
    return this.httpCliente.delete(`${this.API}/${id}`).pipe(first());
  }
}
