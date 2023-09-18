import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { CompartilhadoModule } from '../shared/compartilhado.module';
import { ClasseFormularioComponent } from './containers/classe-formulario/classe-formulario.component';
import { ClasseRoutingModule } from './classe-routing.module';
import { ClasseComponent } from './containers/classe/classe.component';
import { ClasseListaComponent } from './components/classe-lista/classe-lista.component';


@NgModule({
  declarations: [
    ClasseComponent,
    ClasseFormularioComponent,
    ClasseListaComponent
  ],
  imports: [
    CommonModule,
    ClasseRoutingModule,
    CompartilhadoModule,
    AppMaterialModule,
    ReactiveFormsModule
  ]
})
export class ClasseModule { }
