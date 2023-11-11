import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ClienteComponent} from './containers/cliente/cliente.component';
import {ClienteFormularioComponent} from './containers/cliente-formulario/cliente-formulario.component';
import {CompartilhadoModule} from "../shared/compartilhado.module";
import {AppMaterialModule} from "../shared/app-material/app-material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {ClienteRoutingModule} from './cliente-routing.module';
import {ClienteListaComponent} from './components/cliente-lista/cliente-lista.component';

@NgModule({
  declarations: [
    ClienteComponent,
    ClienteFormularioComponent,
    ClienteListaComponent
  ],
  imports: [
    CommonModule,
    ClienteRoutingModule,
    CompartilhadoModule,
    AppMaterialModule,
    ReactiveFormsModule
  ]
})
export class ClienteModule { }
