import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Item } from '../../model/item';

@Component({
  selector: 'app-item-lista',
  templateUrl: './item-lista.component.html',
  styleUrls: ['./item-lista.component.scss'],
})
export class ItemListaComponent {
  @Input() itens: Item[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = ['id', 'numSerie', 'dtaAquisicao', 'tipoItem', 'titulo', 'acoes'];

  constructor() {}

  ngOnInit(): void {}

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(item: Item){
    this.editar.emit(item);
  }

  onRemove(item: Item){
    this.excluir.emit(item);
  }
}
