import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first } from 'rxjs/operators';

import { Item } from '../model/item';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private readonly API = 'api/item';

  constructor(private httpCliente: HttpClient) { }

  listar() {
    return this.httpCliente.get<Item[]>(this.API)
      .pipe(
        first()
        //delay(5000),
        // tap(item => console.log(item))
      );
  }

  buscarPorId(id: number) {
    return this.httpCliente.get<Item>(`${this.API}/${id}`);
  }

  salvar(registro: Partial<Item>) {
    if (registro.id != 0) {
      return this.atualizar(registro);
    }
    return this.criar(registro);
  }

  private criar(registro: Partial<Item>) {
    return this.httpCliente.post<Item>(this.API, registro).pipe(first());
  }

  private atualizar(registro: Partial<Item>) {
    return this.httpCliente
      .put<Item>(`${this.API}/${registro.id}`, registro)
      .pipe(first());
  }

  excluir(id: number) {
    return this.httpCliente.delete(`${this.API}/${id}`).pipe(first());
  }
}
