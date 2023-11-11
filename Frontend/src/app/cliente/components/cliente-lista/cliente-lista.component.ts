import {Component, EventEmitter, Input, Output} from '@angular/core';

import {Cliente} from "../../model/cliente";

@Component({
  selector: 'app-cliente-lista',
  templateUrl: './cliente-lista.component.html',
  styleUrls: ['./cliente-lista.component.scss']
})
export class ClienteListaComponent {
  @Input() clientes: Cliente[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  readonly colunasExibidas = [
    'id',
    'numeroInscricao',
    'nome',
    'dataNascimento',
    'sexo',
    'estahAtivo',
    'acoes',
  ];

  ngOnInit(): void {}

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(cliente: Cliente){
    this.editar.emit(cliente);
  }

  onRemove(cliente: Cliente){
    this.excluir.emit(cliente);
  }
}
