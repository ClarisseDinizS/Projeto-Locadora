import { Component, EventEmitter, Input, Output } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';

import { Titulo } from '../../model/titulo';

@Component({
  selector: 'app-titulo-lista',
  templateUrl: './titulo-lista.component.html',
  styleUrls: ['./titulo-lista.component.scss'],
})
export class TituloListaComponent {
  @Input() titulos: Titulo[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = ['id', 'nome', 'ano', 'categoria', 'diretor', 'acoes'];


  constructor() {}

  ngOnInit(): void {}

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
