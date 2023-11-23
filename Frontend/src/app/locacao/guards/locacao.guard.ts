import { Cliente } from './../../socio/model/cliente';
import { Item } from './../../item/model/item';
import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { LocacaoService } from '../services/locacao.service';
import { Locacao } from '../model/locacao';
import { Diretor } from 'src/app/diretor/model/diretor';
import { Classe } from 'src/app/classe/model/classe';
import { Titulo } from 'src/app/titulo/model/titulo';

@Injectable({
  providedIn: 'root',
})
export class LocacaoResolver implements Resolve<Locacao> {
  constructor(private service: LocacaoService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Locacao> {
    if (route.params && route.params['id']) {
      return this.service.buscarPorId(route.params['id']);
    }

    const cliente = {} as Cliente;
    const item = {} as Item;

    return of({
      id: 0,
      dtLocacao: new Date(),
      dtDevolucaoPrevista: new Date(),
      dtDevolucaoEfetiva: new Date(),
      valorCobrado: 0,
      multaCobrada: 0,
      item: item,
      cliente: cliente,
    });
  }
}
