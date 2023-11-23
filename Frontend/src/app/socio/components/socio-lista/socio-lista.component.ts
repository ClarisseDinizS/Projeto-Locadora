import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { PaginacaoTraduzida } from 'src/app/shared/paginacaoTraduzida/paginacao-traduzida';

import { Socio } from '../../model/socio';
import { SocioService } from '../../services/socio.service';

@Component({
  selector: 'app-socio-lista',
  templateUrl: './socio-lista.component.html',
  styleUrls: ['./socio-lista.component.scss'],
  providers: [{ provide: MatPaginatorIntl, useClass: PaginacaoTraduzida }],
})
export class SocioListaComponent {

  @Input() socios: Socio[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = [
    'numeroInscricao',
    'nome',
    'dataNascimento',
    'sexo',
    'estahAtivo',
    'acoes',
  ];

  dataSource = new MatTableDataSource<Socio>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private servico: SocioService, private router: Router) { }

  ngAfterViewInit() {
    this.dataSource = new MatTableDataSource(this.socios);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  onToggleChange(socio: Socio) {
    socio.estahAtivo = socio.estahAtivo === 'Sim' ? 'Não' : 'Sim';
    this.servico.salvar(socio).subscribe(() => {
      // Recarrega a página após salvar
      this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        this.router.navigate(['socio']);
      });
    });
  }

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(socio: Socio) {
    this.editar.emit(socio);
  }

  onRemove(socio: Socio) {
    this.excluir.emit(socio);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
