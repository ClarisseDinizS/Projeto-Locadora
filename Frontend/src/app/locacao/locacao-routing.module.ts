import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LocacaoComponent} from "./containers/locacao/locacao.component";
import {LocacaoFormularioComponent} from "./containers/locacao-formulario/locacao-formulario.component";
import {LocacaoResolver} from "./guards/locacao.guard";

const routes: Routes = [
  { path: '', component: LocacaoComponent },
  { path: 'novo', component: LocacaoFormularioComponent },
  {
    path: 'editar/:id', component: LocacaoFormularioComponent, resolve: { item: LocacaoResolver },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocacaoRoutingModule { }
