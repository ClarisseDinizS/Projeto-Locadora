import { registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import localePt from '@angular/common/locales/pt';
import { LOCALE_ID, NgModule } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppMaterialModule } from './shared/app-material/app-material.module';
import { DiretorComponent } from './diretor/diretor.component';
import { AtorComponent } from './ator/containers/ator/ator.component';
import { DiretorFormularioComponent } from './diretor/containers/diretor-formulario/diretor-formulario.component';
import { DiretorListaComponent } from './diretor/components/diretor-lista/diretor-lista.component';

@NgModule({
  declarations: [
    AppComponent,
    DiretorComponent,
    AtorComponent,
    DiretorFormularioComponent,
    DiretorListaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppMaterialModule
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
