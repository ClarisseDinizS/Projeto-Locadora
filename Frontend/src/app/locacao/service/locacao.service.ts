import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {first} from "rxjs/operators";
import {Locacao} from "../model/locacao";

@Injectable({
  providedIn: 'root'
})
export class LocacaoService {

  private readonly API = 'api/locacao';

  constructor(private httpCliente: HttpClient) { }

  listar() {
    return this.httpCliente.get<Locacao[]>(this.API).pipe(first());
  }

  buscarPorId(id: number) {
    return this.httpCliente.get<Locacao>(`${this.API}/${id}`);
  }

  salvar(registro: Partial<Locacao>) {
    console.log(registro);
    if (registro.id != 0) {
      return this.atualizar(registro);
    }
    return this.criar(registro);
  }

  private criar(registro: Partial<Locacao>) {
    return this.httpCliente.post<Locacao>(this.API, registro).pipe(first());
  }

  private atualizar(registro: Partial<Locacao>) {
    return this.httpCliente
      .put<Locacao>(`${this.API}/${registro.id}`, registro)
      .pipe(first());
  }

  excluir(id: number) {
    return this.httpCliente
      .delete(`${this.API}/${id}`, { observe: 'response' })
      .pipe(first());
  }
}
