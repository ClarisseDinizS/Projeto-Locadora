import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Diretor } from '../../model/diretor';

@Component({
  selector: 'app-diretor-lista',
  templateUrl: './diretor-lista.component.html',
  styleUrls: ['./diretor-lista.component.scss'],
})
export class DiretorListaComponent {
  @Input() diretores: Diretor[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = ['id', 'nome', 'acoes'];

  constructor() {}

  ngOnInit(): void {}

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(diretor: Diretor){
    this.editar.emit(diretor);
  }

  onRemove(diretor: Diretor){
    this.excluir.emit(diretor);
  }
}