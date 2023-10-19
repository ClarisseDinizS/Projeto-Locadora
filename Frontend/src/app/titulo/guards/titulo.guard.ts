import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Titulo } from '../model/titulo';
import { TituloService } from '../services/titulo.service';
import {Diretor} from "../../diretor/model/diretor";
import {Classe} from "../../classe/model/classe";

@Injectable({
  providedIn: 'root'
})
export class TituloResolver implements Resolve<Titulo> {

  constructor(private service: TituloService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Titulo> {
    if (route.params && route.params['id']) {
      return this.service.buscarPorId(route.params['id']);
    }

    // Criar uma instância de Diretor aqui
    const diretor: Diretor = {
      // Preencha os campos do Diretor conforme sua lógica
      // Exemplo:
      id: 0,
      nome: ''
    };

    // Criar uma instância de Classe aqui
    const classe: Classe = {
      id: 0,
      nome: '',
      valor: 0,
      data: new Date()
    };

    return of({ id: 0, nome: '', ano: new Date(), sinopse: '', categoria: '', diretor: diretor, classe: classe, atores: [] });
  }
}
