import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LocacaoComponent } from './containers/locacao/locacao.component';
import { LocacaoFormularioComponent } from './containers/locacao-formulario/locacao-formulario.component';
import { CompartilhadoModule } from '../shared/compartilhado.module';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { LocacaoRoutingModule } from './locacao-routing.module';
import { LocacaoListaComponent } from './components/locacao-lista/locacao-lista.component';

@NgModule({
  declarations: [
    LocacaoComponent,
    LocacaoFormularioComponent,
    LocacaoListaComponent,
  ],
  imports: [
    CommonModule,
    LocacaoRoutingModule,
    CompartilhadoModule,
    AppMaterialModule,
    ReactiveFormsModule,
  ],
})
export class LocacaoModule { }
