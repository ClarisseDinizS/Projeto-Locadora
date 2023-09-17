import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AtoresRoutingModule } from './atores-routing.module';
import { AtoresComponent } from './atores/atores.component';


@NgModule({
  declarations: [
    AtoresComponent
  ],
  imports: [
    CommonModule,
    AtoresRoutingModule
  ]
})
export class AtoresModule { }
