import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClasseListaComponent } from './classe-lista.component';

describe('ClasseListaComponent', () => {
  let component: ClasseListaComponent;
  let fixture: ComponentFixture<ClasseListaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClasseListaComponent]
    });
    fixture = TestBed.createComponent(ClasseListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
