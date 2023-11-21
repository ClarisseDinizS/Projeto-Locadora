import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { first } from 'rxjs/operators';
import { Socio } from '../model/socio';
import {Cliente} from "../model/cliente";

@Injectable({
  providedIn: 'root',
})
export class SocioService {
  private readonly API = 'api/socio';
  private readonly APICliente = 'api/cliente';

  constructor(private httpCliente: HttpClient) { }

  listar() {
    return this.httpCliente.get<Socio[]>(this.API).pipe(first());
  }

  listarCliente() {
    return this.httpCliente.get<Cliente[]>(this.APICliente).pipe(first());
  }

  buscarPorId(id: number) {
    return this.httpCliente.get<Socio>(`${this.API}/${id}`);
  }

  salvar(registro: Partial<Socio>) {
    if (registro.id != 0) {
      return this.atualizar(registro);
    }
    return this.criar(registro);
  }

  private criar(registro: Partial<Socio>) {
    return this.httpCliente.post<Socio>(this.API, registro).pipe(first());
  }

  private criarCliente(registro: Partial<Cliente>) {
    return this.httpCliente.post<Cliente>(this.APICliente, registro).pipe(first());
  }

  private atualizar(registro: Partial<Socio>) {
    return this.httpCliente
      .put<Socio>(`${this.API}/${registro.id}`, registro)
      .pipe(first());
  }

  excluir(id: number) {
    return this.httpCliente
      .delete(`${this.API}/${id}`, { observe: 'response' })
      .pipe(first());
  }
}
