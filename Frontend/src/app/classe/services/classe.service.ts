import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs/operators';

import { Classe } from '../model/classe';

@Injectable({
  providedIn: 'root'
})
export class ClasseService{

  private readonly API = 'api/classe';

  constructor(private httpCliente: HttpClient) { }

  listar() {
    return this.httpCliente.get<Classe[]>(this.API)
      .pipe(
        first()
      );
  }

  buscarPorId(id: number) {
    return this.httpCliente.get<Classe>(`${this.API}/${id}`);
  }

  salvar(registro: Partial<Classe>) {
    if (registro.id != 0) {
      return this.atualizar(registro);
    }
    return this.criar(registro);
  }

  private criar(registro: Partial<Classe>) {
    return this.httpCliente.post<Classe>(this.API, registro).pipe(first());
  }

  private atualizar(registro: Partial<Classe>) {
    return this.httpCliente
      .put<Classe>(`${this.API}/${registro.id}`, registro)
      .pipe(first());
  }

  excluir(id: number) {
    return this.httpCliente.delete(`${this.API}/${id}`, { observe: 'response' }).pipe(first());
  }
}
