import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Locacao } from '../../model/locacao';
import { LocacaoService } from '../../services/locacao.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-locacao-lista',
  templateUrl: './locacao-lista.component.html',
  styleUrls: ['./locacao-lista.component.scss'],
})
export class LocacaoListaComponent {

  @Input() locacoes: Locacao[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() excluir = new EventEmitter(false);

  constructor(private servico: LocacaoService,
    private router: Router) {}

  readonly colunasExibidas = [
    'id',
    'dtLocacao',
    'dtDevolucaoPrevista',
    'dtDevolucaoEfetiva',
    'valorCobrado',
    'multaCobrada',
    'acoes',
  ];

  ngOnInit(): void { console.log(this.locacoes) }

  onAdd() {
    this.adicionar.emit(true);
  }

  onEdit(locacao: Locacao) {
    this.editar.emit(locacao);
  }

  onRemove(locacao: Locacao) {
    this.excluir.emit(locacao);
  }

}
