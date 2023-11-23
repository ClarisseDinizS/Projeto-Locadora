import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PaginacaoTraduzida } from 'src/app/shared/paginacaoTraduzida/paginacao-traduzida';

import { Classe } from '../../model/classe';

@Component({
  selector: 'app-classe-lista',
  templateUrl: './classe-lista.component.html',
  styleUrls: ['./classe-lista.component.scss'],
  providers: [{ provide: MatPaginatorIntl, useClass: PaginacaoTraduzida }],
})
export class ClasseListaComponent {

  @Input() classes: Classe[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = ['id', 'nome', 'valor', 'data', 'acoes'];

  dataSource = new MatTableDataSource<Classe>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource = new MatTableDataSource(this.classes);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(classe: Classe){
    this.editar.emit(classe);
  }

  onRemove(classe: Classe){
    this.excluir.emit(classe);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
