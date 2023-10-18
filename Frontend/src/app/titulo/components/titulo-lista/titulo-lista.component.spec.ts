import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TituloListaComponent } from './titulo-lista.component';

describe('TituloListaComponent', () => {
  let component: TituloListaComponent;
  let fixture: ComponentFixture<TituloListaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TituloListaComponent]
    });
    fixture = TestBed.createComponent(TituloListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
