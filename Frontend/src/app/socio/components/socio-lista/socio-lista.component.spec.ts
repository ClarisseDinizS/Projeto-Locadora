import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SocioListaComponent } from './socio-lista.component';

describe('SocioListaComponent', () => {
  let component: SocioListaComponent;
  let fixture: ComponentFixture<SocioListaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SocioListaComponent]
    });
    fixture = TestBed.createComponent(SocioListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
