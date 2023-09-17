import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtorFormularioComponent } from './ator-formulario.component';

describe('AtorFormularioComponent', () => {
  let component: AtorFormularioComponent;
  let fixture: ComponentFixture<AtorFormularioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtorFormularioComponent]
    });
    fixture = TestBed.createComponent(AtorFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
