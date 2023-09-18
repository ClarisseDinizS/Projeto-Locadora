import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClasseFormularioComponent } from './classe-formulario.component';

describe('ClasseFormularioComponent', () => {
  let component: ClasseFormularioComponent;
  let fixture: ComponentFixture<ClasseFormularioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClasseFormularioComponent]
    });
    fixture = TestBed.createComponent(ClasseFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
