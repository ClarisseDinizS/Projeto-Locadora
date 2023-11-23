import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PaginacaoTraduzida } from 'src/app/shared/paginacaoTraduzida/paginacao-traduzida';

import { Diretor } from '../../model/diretor';

@Component({
  selector: 'app-diretor-lista',
  templateUrl: './diretor-lista.component.html',
  styleUrls: ['./diretor-lista.component.scss'],
  providers: [{ provide: MatPaginatorIntl, useClass: PaginacaoTraduzida }],
})
export class DiretorListaComponent {

  @Input() diretores: Diretor[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = ['id', 'nome', 'acoes'];

  dataSource = new MatTableDataSource<Diretor>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource = new MatTableDataSource(this.diretores);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(diretor: Diretor){
    this.editar.emit(diretor);
  }

  onRemove(diretor: Diretor){
    this.excluir.emit(diretor);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
