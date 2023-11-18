import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SocioFormularioComponent } from './socio-formulario.component';

describe('SocioFormularioComponent', () => {
  let component: SocioFormularioComponent;
  let fixture: ComponentFixture<SocioFormularioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SocioFormularioComponent],
    });
    fixture = TestBed.createComponent(SocioFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
