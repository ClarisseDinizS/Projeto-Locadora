import { registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import localePt from '@angular/common/locales/pt';
import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { AppMaterialModule } from './shared/app-material/app-material.module';
import { ClienteListaComponent } from './app/cliente/components/cliente-lista/cliente-lista.component';
import { ClienteFormularioComponent } from './cliente/components/cliente-formulario/cliente-formulario.component';
import { ClienteComponent } from './cliente/containers/cliente/cliente.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    ClienteListaComponent,
    ClienteFormularioComponent,
    ClienteComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppMaterialModule,
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'pt-BR' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor() {
    registerLocaleData(localePt, 'pt-BR'); // Registra os dados de localização para pt-BR
  }
}
