import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './shared/home/home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'ator', loadChildren: () => import('./ator/ator.module').then(m => m.AtorModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
