import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Classe } from '../../model/classe';

@Component({
  selector: 'app-classe-lista',
  templateUrl: './classe-lista.component.html',
  styleUrls: ['./classe-lista.component.scss'],
})
export class ClasseListaComponent {
  @Input() classes: Classe[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = ['id', 'nome', 'valor', 'data', 'acoes'];

  constructor() {}

  ngOnInit(): void {}

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(classe: Classe){
    this.editar.emit(classe);
  }

  onRemove(classe: Classe){
    this.excluir.emit(classe);
  }
}
