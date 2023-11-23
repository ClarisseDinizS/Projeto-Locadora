import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable, of } from 'rxjs';

import { Item } from '../model/item';
import { ItemService } from '../services/item.service';
import { Diretor } from '../../diretor/model/diretor';
import { Classe } from '../../classe/model/classe';
import { Titulo } from 'src/app/titulo/model/titulo';

@Injectable({
  providedIn: 'root',
})
export class ItemResolver implements Resolve<Item> {
  constructor(private service: ItemService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Item> {
    if (route.params && route.params['id']) {
      return this.service.buscarPorId(route.params['id']);
    }

    const titulo = {} as Titulo;

    return of({
      id: 0,
      numSerie: 0,
      dtaAquisicao: new Date(),
      tipoItem: '',
      titulo: titulo,
    });
  }
}
