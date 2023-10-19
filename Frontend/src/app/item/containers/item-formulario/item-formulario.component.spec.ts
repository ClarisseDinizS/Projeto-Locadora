import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemFormularioComponent } from './item-formulario.component';

describe('ItemFormularioComponent', () => {
  let component: ItemFormularioComponent;
  let fixture: ComponentFixture<ItemFormularioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ItemFormularioComponent]
    });
    fixture = TestBed.createComponent(ItemFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
