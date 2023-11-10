import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClienteFormularioComponent } from './cliente-formulario.component';

describe('ClienteFormularioComponent', () => {
  let component: ClienteFormularioComponent;
  let fixture: ComponentFixture<ClienteFormularioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClienteFormularioComponent]
    });
    fixture = TestBed.createComponent(ClienteFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
