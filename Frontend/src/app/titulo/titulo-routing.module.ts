import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TituloFormularioComponent } from './containers/titulo-formulario/titulo-formulario.component';
import { TituloComponent } from './containers/titulo/titulo.component';
import { TituloResolver } from './guards/titulo.guard';

const routes: Routes = [
  { path: '', component: TituloComponent },
  { path: 'novo', component: TituloFormularioComponent },
  {
    path: 'editar/:id', component: TituloFormularioComponent, resolve: { titulo: TituloResolver },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TituloRoutingModule { }
