import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SocioComponent } from './containers/socio/socio.component';
import { SocioFormularioComponent } from './containers/socio-formulario/socio-formulario.component';
import { CompartilhadoModule } from '../shared/compartilhado.module';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { SocioRoutingModule } from './socio-routing.module';
import { SocioListaComponent } from './components/socio-lista/socio-lista.component';

@NgModule({
  declarations: [
    SocioComponent,
    SocioFormularioComponent,
    SocioListaComponent,
  ],
  imports: [
    CommonModule,
    SocioRoutingModule,
    CompartilhadoModule,
    AppMaterialModule,
    ReactiveFormsModule,
  ],
})
export class SocioModule { }
