import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AtorFormularioComponent } from './ator-formulario/ator-formulario.component';
import { AtorComponent } from './ator/ator.component';

const routes: Routes = [
  { path: '', component: AtorComponent },
  { path: 'novo', component: AtorFormularioComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtoresRoutingModule { }
