import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtorListaComponent } from './ator-lista.component';

describe('AtorListaComponent', () => {
  let component: AtorListaComponent;
  let fixture: ComponentFixture<AtorListaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtorListaComponent]
    });
    fixture = TestBed.createComponent(AtorListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
