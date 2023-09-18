import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { CompartilhadoModule } from '../shared/compartilhado.module';
import { AtorFormularioComponent } from './containers/ator-formulario/ator-formulario.component';
import { AtorRoutingModule } from './ator-routing.module';
import { AtorComponent } from './containers/ator/ator.component';
import { AtorListaComponent } from './components/ator-lista/ator-lista.component';


@NgModule({
  declarations: [
    AtorComponent,
    AtorFormularioComponent,
    AtorListaComponent
  ],
  imports: [
    CommonModule,
    AtorRoutingModule,
    CompartilhadoModule,
    AppMaterialModule,
    ReactiveFormsModule
  ]
})
export class AtorModule { }
