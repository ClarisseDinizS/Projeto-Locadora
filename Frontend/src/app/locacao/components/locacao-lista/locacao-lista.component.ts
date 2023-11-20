import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Locacao} from "../../model/locacao";
import {Sort} from "@angular/material/sort";

@Component({
  selector: 'app-locacao-lista',
  templateUrl: './locacao-lista.component.html',
  styleUrls: ['./locacao-lista.component.scss']
})
export class LocacaoListaComponent {

  @Input() locacao: Locacao[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = ['id', 'dtLocacao', 'dtDevolucaoPrevista', 'dtDevolucaoEfetiva',
    'valorCobrado', 'multaCobrada','acoes'];


  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(locacao: Locacao){
    this.editar.emit(locacao);
  }

  onRemove(locacao: Locacao){
    this.excluir.emit(locacao);
  }

  sortData(sort: Sort) {
    const sortedData = this.locacao.slice();

    if (!sort.active || sort.direction === '') {
      this.locacao = sortedData;
      return;
    }

  }
}
