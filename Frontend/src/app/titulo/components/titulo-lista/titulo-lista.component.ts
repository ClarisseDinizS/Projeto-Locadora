import {Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';

import { Titulo } from '../../model/titulo';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, MatPaginatorIntl} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {PaginacaoTraduzida} from "../../../shared/paginacaoTraduzida/paginacao-traduzida";
7

@Component({
  selector: 'app-titulo-lista',
  templateUrl: './titulo-lista.component.html',
  styleUrls: ['./titulo-lista.component.scss'],
  providers: [{ provide: MatPaginatorIntl, useClass: PaginacaoTraduzida }],
})
export class TituloListaComponent {
  @Input() titulos: Titulo[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = ['id', 'nome', 'ano', 'categoria', 'diretor', 'atores', 'acoes'];

  dataSource = new MatTableDataSource<Titulo>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource = new MatTableDataSource(this.titulos);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  constructor() {}

  ngOnInit(): void {}

  getNomesAtores(titulo: Titulo): string {
    // Verifica se há atores no título
    if (titulo.atores && titulo.atores.length > 0) {
      // Mapeia apenas os nomes dos atores
      const nomesAtores = titulo.atores.map(ator => ator.nome);

      // Retorna os nomes dos atores como uma string separada por vírgulas
      return nomesAtores.join(', ');
    } else {
      // Caso não haja atores, retorna uma string vazia ou outra indicação desejada
      return 'Sem atores';
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(titulo: Titulo){
    this.editar.emit(titulo);
  }

  onRemove(titulo: Titulo){
    this.excluir.emit(titulo);
  }

}
