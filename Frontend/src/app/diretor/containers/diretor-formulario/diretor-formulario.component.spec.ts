import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiretorFormularioComponent } from './diretor-formulario.component';

describe('DiretorFormularioComponent', () => {
  let component: DiretorFormularioComponent;
  let fixture: ComponentFixture<DiretorFormularioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DiretorFormularioComponent]
    });
    fixture = TestBed.createComponent(DiretorFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
