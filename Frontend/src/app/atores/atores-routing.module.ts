import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AtoresComponent } from './atores/atores.component';

const routes: Routes = [
  { path: '', component: AtoresComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtoresRoutingModule { }
