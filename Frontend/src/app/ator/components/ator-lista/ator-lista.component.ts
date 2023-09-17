import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Ator } from '../../model/ator';

@Component({
  selector: 'app-ator-lista',
  templateUrl: './ator-lista.component.html',
  styleUrls: ['./ator-lista.component.scss'],
})
export class AtorListaComponent {
  @Input() atores: Ator[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);

  readonly colunasExibidas = ['id', 'nome', 'acoes'];

  constructor() {}

  ngOnInit(): void {}

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(ator: Ator){
    this.editar.emit(ator);
  }
}
