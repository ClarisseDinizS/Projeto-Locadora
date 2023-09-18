import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Classe } from '../model/classe';
import { ClasseService } from '../services/classe.service';

@Injectable({
  providedIn: 'root'
})
export class ClasseResolver implements Resolve<Classe> {

  constructor(private service: ClasseService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Classe> {
    if (route.params && route.params['id']) {
      return this.service.buscarPorId(route.params['id']);
    }
    return of({ id: 0, nome: '', valor: 0, data: new Date(), lessons: [] });
  }
}
