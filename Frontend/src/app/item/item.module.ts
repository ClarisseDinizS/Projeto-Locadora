import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { CompartilhadoModule } from '../shared/compartilhado.module';
import { ItemFormularioComponent } from './containers/item-formulario/item-formulario.component';
import { ItemRoutingModule } from './item-routing.module';
import { ItemComponent } from './containers/item/item.component';
import { ItemListaComponent } from './components/item-lista/item-lista.component';


@NgModule({
  declarations: [
    ItemComponent,
    ItemFormularioComponent,
    ItemListaComponent
  ],
  imports: [
    CommonModule,
    ItemRoutingModule,
    CompartilhadoModule,
    AppMaterialModule,
    ReactiveFormsModule
  ]
})
export class ItemModule { }
