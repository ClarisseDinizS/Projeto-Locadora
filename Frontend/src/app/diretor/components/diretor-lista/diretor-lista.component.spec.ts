import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiretorListaComponent } from './diretor-lista.component';

describe('DiretorListaComponent', () => {
  let component: DiretorListaComponent;
  let fixture: ComponentFixture<DiretorListaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DiretorListaComponent]
    });
    fixture = TestBed.createComponent(DiretorListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
