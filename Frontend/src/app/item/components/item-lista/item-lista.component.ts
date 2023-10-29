import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Item } from '../../model/item';
import { Sort } from '@angular/material/sort';

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

  readonly colunasExibidas = [
    'id',
    'numSerie',
    'dtaAquisicao',
    'tipoItem',
    'titulo',
    'acoes',
  ];

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(item: Item) {
    this.editar.emit(item);
  }

  onRemove(item: Item) {
    this.excluir.emit(item);
  }

  sortData(sort: Sort) {
    const sortedData = this.itens.slice();

    if (!sort.active || sort.direction === '') {
      this.itens = sortedData;
      return;
    }

    sortedData.sort((a, b) => {
      const ascendente = sort.direction === 'asc';

      switch (sort.active) {
        case 'id':
          return this.comparar(a.id, b.id, ascendente);
        case 'numSerie':
          return this.comparar(a.numSerie, b.numSerie, ascendente);
        case 'dtaAquisicao':
          return this.comparar(a.dtaAquisicao, b.dtaAquisicao, ascendente);
        case 'tipoItem':
          return this.comparar(a.tipoItem, b.tipoItem, ascendente);
        case 'titulo':
          return this.comparar(a.titulo.nome, b.titulo.nome, ascendente);
        default:
          return 0;
      }
    });

    this.itens = sortedData;
  }

  comparar(
    a: number | string | Date,
    b: number | string | Date,
    isAsc: boolean
  ) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
}
