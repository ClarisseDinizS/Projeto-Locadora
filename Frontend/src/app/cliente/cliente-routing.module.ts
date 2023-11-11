import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ClienteComponent} from "./containers/cliente/cliente.component";
import {ClienteFormularioComponent} from "./containers/cliente-formulario/cliente-formulario.component";
import {ClienteResolver} from "./guards/cliente.guard";

const routes: Routes = [
  { path: '', component: ClienteComponent },
  { path: 'novo', component: ClienteFormularioComponent },
  {
    path: 'editar/:id', component: ClienteFormularioComponent, resolve: { cliente: ClienteResolver },
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClienteRoutingModule { }
