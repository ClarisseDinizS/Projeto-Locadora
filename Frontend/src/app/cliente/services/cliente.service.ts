import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { delay, first, tap } from 'rxjs/operators';
import {Cliente} from "../model/cliente";

@Injectable({
  providedIn: 'root'
})

export class ClienteService{

  private readonly API = 'api/cliente';

  constructor(private httpCliente: HttpClient) { }

  listar() {
    return this.httpCliente.get<Cliente[]>(this.API)
      .pipe(
        first()
      );
  }

  buscarPorId(id: number) {
    return this.httpCliente.get<Cliente>(`${this.API}/${id}`);
  }

  salvar(registro: Partial<Cliente>) {
    if (registro.id != 0) {
      return this.atualizar(registro);
    }
    return this.criar(registro);
  }

  private criar(registro: Partial<Cliente>) {
    return this.httpCliente.post<Cliente>(this.API, registro).pipe(first());
  }

  private atualizar(registro: Partial<Cliente>) {
    return this.httpCliente
      .put<Cliente>(`${this.API}/${registro.id}`, registro)
      .pipe(first());
  }

  excluir(id: number) {
    return this.httpCliente.delete(`${this.API}/${id}`, { observe: 'response' }).pipe(first());
  }
}
