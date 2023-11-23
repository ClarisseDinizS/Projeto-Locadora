import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Titulo } from '../model/titulo';
import { TituloService } from '../services/titulo.service';
import {Diretor} from "../../diretor/model/diretor";
import {Classe} from "../../classe/model/classe";
import { Ator } from 'src/app/ator/model/ator';

@Injectable({
  providedIn: 'root'
})
export class TituloResolver implements Resolve<Titulo> {

  constructor(private service: TituloService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Titulo> {
    if (route.params && route.params['id']) {
      return this.service.buscarPorId(route.params['id']);
    }

    const diretor = {} as Diretor;
    const classe = {} as Classe;
    const atores = [{} as Ator];

    return of({ id: 0, nome: '', ano: new Date().getFullYear(), sinopse: '', categoria: '', diretor: diretor, classe: classe, atores: atores });
  }
}
