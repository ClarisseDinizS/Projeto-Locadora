import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { CompartilhadoModule } from '../shared/compartilhado.module';
import { DiretorFormularioComponent } from './containers/diretor-formulario/diretor-formulario.component';
import { DiretorRoutingModule } from './diretor-routing.module';
import { DiretorComponent } from './containers/diretor/diretor.component';
import { DiretorListaComponent } from './components/diretor-lista/diretor-lista.component';


@NgModule({
  declarations: [
    DiretorComponent,
    DiretorFormularioComponent,
    DiretorListaComponent
  ],
  imports: [
    CommonModule,
    DiretorRoutingModule,
    CompartilhadoModule,
    AppMaterialModule,
    ReactiveFormsModule
  ]
})
export class DiretorModule { }
