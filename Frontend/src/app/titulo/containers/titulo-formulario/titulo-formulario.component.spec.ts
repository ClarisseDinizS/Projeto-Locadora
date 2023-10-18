import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TituloFormularioComponent } from './titulo-formulario.component';

describe('TituloFormularioComponent', () => {
  let component: TituloFormularioComponent;
  let fixture: ComponentFixture<TituloFormularioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TituloFormularioComponent]
    });
    fixture = TestBed.createComponent(TituloFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
