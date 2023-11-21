import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Titulo} from "../../titulo/model/titulo";
import {TituloService} from "../../titulo/services/titulo.service";
import {Observable, of} from "rxjs";
import {Locacao} from "../model/locacao";
import {LocacaoService} from "../service/locacao.service";
import {Item} from "../../item/model/item";
import {Cliente} from "../../socio/model/cliente";
import {Diretor} from "../../diretor/model/diretor";
import {Classe} from "../../classe/model/classe";
import {Socio} from "../../socio/model/socio";

@Injectable({
  providedIn: 'root'
})
export class LocacaoResolver implements Resolve<Locacao> {

  constructor(private service: LocacaoService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot):
    Observable<Locacao> {
    if (route.params && route.params['id']) {
      return this.service.buscarPorId(route.params['id']);
    }


    const diretor: Diretor = {
      id: 0,
      nome: '',
    };

    const classe: Classe = {
      id: 0,
      nome: '',
      valor: 0,
      data: new Date(),
    };

    const titulo: Titulo = {
      id: 0,
      nome: '',
      ano: new Date(),
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
      tipoItem: '',
      titulo: titulo
    };

    const cliente: Cliente = {
      id: 0,
      numeroInscricao: 0,
      nome: '',
      dataNascimento: new Date(),
      sexo: '',
      estahAtivo: ''
    };

    return of({
      id: 0,
      dtLocacao: new Date(),
      dtDevolucaoPrevista: new Date(),
      dtDevolucaoEfetiva: new Date(),
      valorCobrado: 0,
      multaCobrada: 0,
      item: item,
      cliente: cliente
    });
  }
}
