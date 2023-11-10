import { Component, EventEmitter, Input, Output } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';

import { Titulo } from '../../model/titulo';

@Component({
  selector: 'app-titulo-lista',
  templateUrl: './titulo-lista.component.html',
  styleUrls: ['./titulo-lista.component.scss'],
  animations: [
    trigger('detalhesExpandido', [
      state('collapsed', style({height: '0px', minHeight: '0', display: 'none'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class TituloListaComponent {
  @Input() titulos: Titulo[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = ['id', 'nome', 'ano', 'categoria', 'diretor', 'acoes'];
  elementoExpandido!: Titulo;

  constructor() {}

  ngOnInit(): void {}

  toggleRow(row: any): void {
    this.elementoExpandido = this.elementoExpandido === row ? null : row;
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
