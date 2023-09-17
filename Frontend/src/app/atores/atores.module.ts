import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AtoresRoutingModule } from './atores-routing.module';
import { CompartilhadoModule } from '../shared/compartilhado.module';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { AtoresComponent } from './atores/atores.component';


@NgModule({
  declarations: [
  
  
    AtoresComponent
  ],
  imports: [
    CommonModule,
    AtoresRoutingModule,
    CompartilhadoModule,
    AppMaterialModule
  ]
})
export class AtoresModule { }
