import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './shared/home/home/home.component';
import {NavBarComponent} from "./nav-bar/nav-bar.component";
import {LocacaoModule} from "./locacao/locacao.module";

const routes: Routes = [{
  path: '', component: NavBarComponent, children: [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'ator', loadChildren: () => import('./ator/ator.module').then(m => m.AtorModule) },
  { path: 'classe', loadChildren: () => import('./classe/classe.module').then(m => m.ClasseModule) },
  { path: 'socio', loadChildren: () => import('./socio/socio.module').then(m => m.SocioModule) },
  { path: 'locacao', loadChildren: () => import('./locacao/locacao.module').then(m => m.LocacaoModule) },
  { path: 'diretor', loadChildren: () => import('./diretor/diretor.module').then(m => m.DiretorModule) },
  { path: 'titulo', loadChildren: () => import('./titulo/titulo.module').then(m => m.TituloModule) },
  { path: 'item', loadChildren: () => import('./item/item.module').then(m => m.ItemModule) }
]}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
