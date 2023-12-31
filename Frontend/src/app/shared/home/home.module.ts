import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home/home.component';
import { CompartilhadoModule } from '../compartilhado.module';
import { AppMaterialModule } from '../app-material/app-material.module';


@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    CompartilhadoModule,
    AppMaterialModule
  ]
})
export class HomeModule { }
