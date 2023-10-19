import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ItemFormularioComponent } from './containers/item-formulario/item-formulario.component';
import { ItemComponent } from './containers/item/item.component';
import { ItemResolver } from './guards/item.guard';

const routes: Routes = [
  { path: '', component: ItemComponent },
  { path: 'novo', component: ItemFormularioComponent },
  {
    path: 'editar/:id', component: ItemFormularioComponent, resolve: { item: ItemResolver },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ItemRoutingModule { }
