import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AtorFormularioComponent } from './containers/ator-formulario/ator-formulario.component';
import { AtorComponent } from './containers/ator/ator.component';
import { AtorResolver } from './guards/ator.guard';

const routes: Routes = [
  { path: '', component: AtorComponent },
  { path: 'novo', component: AtorFormularioComponent },
  {
    path: 'editar/:id', component: AtorFormularioComponent, resolve: { ator: AtorResolver },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtoresRoutingModule { }
