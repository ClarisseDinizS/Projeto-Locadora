import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Locacao } from '../../model/locacao';
import { Sort } from '@angular/material/sort';

@Component({
  selector: 'app-locacao-lista',
  templateUrl: './locacao-lista.component.html',
  styleUrls: ['./locacao-lista.component.scss'],
})
export class LocacaoListaComponent {
  @Input() locacoes: Locacao[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = [
    'id',
    'dtLocacao',
    'dtDevolucaoPrevista',
    'dtDevolucaoEfetiva',
    'valorCobrado',
    'multaCobrada',
    'acoes',
  ];

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(locacao: Locacao) {
    this.editar.emit(locacao);
  }

  onRemove(locacao: Locacao) {
    this.excluir.emit(locacao);
  }

  sortData(sort: Sort) {
    const sortedData = this.locacoes.slice();

    if (!sort.active || sort.direction === '') {
      this.locacoes = sortedData;
      return;
    }

    sortedData.sort((a, b) => {
      const ascendente = sort.direction === 'asc';

      switch (sort.active) {
        case 'id':
          return this.comparar(a.id, b.id, ascendente);
        case 'dtLocacao':
          return this.comparar(a.dtLocacao, b.dtLocacao, ascendente);
        case 'dtDevolucaoPrevista':
          return this.comparar(a.dtDevolucaoPrevista, b.dtDevolucaoPrevista, ascendente);
        case 'dtDevolucaoEfetiva':
          return this.comparar(a.dtDevolucaoEfetiva, b.dtDevolucaoEfetiva, ascendente);
        case 'valorCobrado':
          return this.comparar(a.valorCobrado, b.valorCobrado, ascendente);
          case 'multaCobrada':
            return this.comparar(a.multaCobrada, b.multaCobrada, ascendente);
        default:
          return 0;
      }
    });

    this.locacoes = sortedData;
  }

  comparar(
    a: number | string | Date,
    b: number | string | Date,
    isAsc: boolean
  ) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
}
