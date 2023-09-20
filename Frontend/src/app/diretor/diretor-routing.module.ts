import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DiretorFormularioComponent } from './containers/diretor-formulario/diretor-formulario.component';
import { DiretorComponent } from './containers/diretor/diretor.component';
import { DiretorResolver } from './guards/diretor.guard';

const routes: Routes = [
  { path: '', component: DiretorComponent },
  { path: 'novo', component: DiretorFormularioComponent },
  {
    path: 'editar/:id', component: DiretorFormularioComponent, resolve: { diretor: DiretorResolver },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DiretorRoutingModule { }
