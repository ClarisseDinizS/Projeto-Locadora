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

    const cliente: Cliente = {
      id: 0,
      nome: '',
      dataNascimento: new Date(),
      sexo: '',
      estahAtivo: 'Sim'
    };

    // Criar uma instância de Diretor aqui
    const diretor: Diretor = {
      id: 0,
      nome: '',
    };

    // Criar uma instância de Classe aqui
    const classe: Classe = {
      id: 0,
      nome: '',
      valor: 0,
      data: new Date(),
    };

    // Criar uma instância de Título aqui
    const titulo: Titulo = {
      id: 0,
      nome: '',
      ano: new Date().getFullYear(),
      sinopse: '',
      categoria: '',
      atores: [],
      diretor: diretor,
      classe: classe,
    };

    const item: Item = {
      id: 0,
      numSerie: 0,
      dtaAquisicao: new Date(),
      tipoItem: 'DVD',
      titulo: titulo,
    };


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
