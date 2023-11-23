import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PaginacaoTraduzida } from 'src/app/shared/paginacaoTraduzida/paginacao-traduzida';

import { Locacao } from '../../model/locacao';

@Component({
  selector: 'app-locacao-lista',
  templateUrl: './locacao-lista.component.html',
  styleUrls: ['./locacao-lista.component.scss'],
  providers: [{ provide: MatPaginatorIntl, useClass: PaginacaoTraduzida }],
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

  dataSource = new MatTableDataSource<Locacao>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource = new MatTableDataSource(this.locacoes);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(locacao: Locacao) {
    this.editar.emit(locacao);
  }

  onRemove(locacao: Locacao) {
    this.excluir.emit(locacao);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
