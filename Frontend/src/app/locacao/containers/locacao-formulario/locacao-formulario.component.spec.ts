import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocacaoFormularioComponent } from './locacao-formulario.component';

describe('LocacaoFormularioComponent', () => {
  let component: LocacaoFormularioComponent;
  let fixture: ComponentFixture<LocacaoFormularioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocacaoFormularioComponent],
    });
    fixture = TestBed.createComponent(LocacaoFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
