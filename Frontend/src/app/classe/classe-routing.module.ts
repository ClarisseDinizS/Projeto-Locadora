import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClasseFormularioComponent } from './containers/classe-formulario/classe-formulario.component';
import { ClasseComponent } from './containers/classe/classe.component';
import { ClasseResolver } from './guards/classe.guard';

const routes: Routes = [
  { path: '', component: ClasseComponent },
  { path: 'novo', component: ClasseFormularioComponent },
  {
    path: 'editar/:id', component: ClasseFormularioComponent, resolve: { classe: ClasseResolver },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClasseRoutingModule { }
