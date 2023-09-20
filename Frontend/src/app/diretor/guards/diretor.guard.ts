import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Diretor } from '../model/diretor';
import { DiretorService } from '../service/diretor.service';

@Injectable({
  providedIn: 'root'
})
export class DiretorResolver implements Resolve<Diretor> {

  constructor(private service: DiretorService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Diretor> {
    if (route.params && route.params['id']) {
      return this.service.buscarPorId(route.params['id']);
    }
    return of({ id: 0, nome: '', lessons: [] });
  }
}