import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { CompartilhadoModule } from '../shared/compartilhado.module';
import { AtorFormularioComponent } from './ator-formulario/ator-formulario.component';
import { AtoresRoutingModule } from './ator-routing.module';
import { AtorComponent } from './ator/ator.component';


@NgModule({
  declarations: [
    AtorComponent,
    AtorFormularioComponent
  ],
  imports: [
    CommonModule,
    AtoresRoutingModule,
    CompartilhadoModule,
    AppMaterialModule
  ]
})
export class AtorModule { }
