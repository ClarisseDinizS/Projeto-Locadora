import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PaginacaoTraduzida } from 'src/app/shared/paginacaoTraduzida/paginacao-traduzida';

import { Item } from '../../model/item';

@Component({
  selector: 'app-item-lista',
  templateUrl: './item-lista.component.html',
  styleUrls: ['./item-lista.component.scss'],
  providers: [{ provide: MatPaginatorIntl, useClass: PaginacaoTraduzida }],
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

  dataSource = new MatTableDataSource<Item>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource = new MatTableDataSource(this.itens);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(item: Item) {
    this.editar.emit(item);
  }

  onRemove(item: Item) {
    this.excluir.emit(item);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
