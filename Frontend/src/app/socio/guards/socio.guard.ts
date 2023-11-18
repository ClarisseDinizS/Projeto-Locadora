import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { SocioService } from '../services/socio.service';
import { Socio } from '../model/socio';

@Injectable({
  providedIn: 'root',
})
export class SocioResolver implements Resolve<Socio> {
  constructor(private service: SocioService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Socio> {
    if (route.params && route.params['id']) {
      return this.service.buscarPorId(route.params['id']);
    }
    return of({
      id: 0,
      numeroInscricao: 0,
      nome: '',
      dataNascimento: new Date(),
      sexo: '',
      estahAtivo: '',
      cpf: '',
      endereco: '',
      telefone: '',
      dependentes: [],
    });
  }
}
