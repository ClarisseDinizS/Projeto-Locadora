import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SocioComponent } from './containers/socio/socio.component';
import { SocioFormularioComponent } from './containers/socio-formulario/socio-formulario.component';
import { SocioResolver } from './guards/socio.guard';

const routes: Routes = [
  { path: '', component: SocioComponent },
  { path: 'novo', component: SocioFormularioComponent },
  {
    path: 'editar/:id',
    component: SocioFormularioComponent,
    resolve: { socio: SocioResolver },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SocioRoutingModule { }
