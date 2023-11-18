import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Socio } from '../../model/socio';

@Component({
  selector: 'app-socio-lista',
  templateUrl: './socio-lista.component.html',
  styleUrls: ['./socio-lista.component.scss'],
})
export class SocioListaComponent {
  @Input() socios: Socio[] = [];
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

  ngOnInit(): void { }

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(socio: Socio) {
    this.editar.emit(socio);
  }

  onRemove(socio: Socio) {
    this.excluir.emit(socio);
  }
}
