import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LocacaoRoutingModule } from './locacao-routing.module';
import {LocacaoComponent} from "./containers/locacao/locacao.component";
import {CompartilhadoModule} from "../shared/compartilhado.module";
import {AppMaterialModule} from "../shared/app-material/app-material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {LocacaoFormularioComponent} from "./containers/locacao-formulario/locacao-formulario.component";
import {LocacaoListaComponent} from "./components/locacao-lista/locacao-lista.component";


@NgModule({
  declarations: [
    LocacaoComponent,
    LocacaoListaComponent,
    LocacaoFormularioComponent
  ],
  imports: [
    CommonModule,
    LocacaoRoutingModule,
    CompartilhadoModule,
    AppMaterialModule,
    ReactiveFormsModule
  ]
})
export class LocacaoModule { }
