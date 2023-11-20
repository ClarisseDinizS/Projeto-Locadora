import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocacaoListaComponent } from './locacao-lista.component';

describe('LocacaoListaComponent', () => {
  let component: LocacaoListaComponent;
  let fixture: ComponentFixture<LocacaoListaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocacaoListaComponent]
    });
    fixture = TestBed.createComponent(LocacaoListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
