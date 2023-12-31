import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PaginacaoTraduzida } from 'src/app/shared/paginacaoTraduzida/paginacao-traduzida';

import { Ator } from '../../model/ator';

@Component({
  selector: 'app-ator-lista',
  templateUrl: './ator-lista.component.html',
  styleUrls: ['./ator-lista.component.scss'],
  providers: [{ provide: MatPaginatorIntl, useClass: PaginacaoTraduzida }],
})
export class AtorListaComponent {

  @Input() atores: Ator[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = ['id', 'nome', 'acoes'];

  dataSource = new MatTableDataSource<Ator>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource = new MatTableDataSource(this.atores);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(ator: Ator) {
    this.editar.emit(ator);
  }

  onRemove(ator: Ator) {
    this.excluir.emit(ator);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
