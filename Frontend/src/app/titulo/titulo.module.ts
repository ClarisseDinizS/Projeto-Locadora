import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { CompartilhadoModule } from '../shared/compartilhado.module';
import { TituloFormularioComponent } from './containers/titulo-formulario/titulo-formulario.component';
import { TituloRoutingModule } from './titulo-routing.module';
import { TituloComponent } from './containers/titulo/titulo.component';
import { TituloListaComponent } from './components/titulo-lista/titulo-lista.component';


@NgModule({
  declarations: [
    TituloComponent,
    TituloFormularioComponent,
    TituloListaComponent
  ],
  imports: [
    CommonModule,
    TituloRoutingModule,
    CompartilhadoModule,
    AppMaterialModule,
    ReactiveFormsModule
  ]
})
export class TituloModule { }
