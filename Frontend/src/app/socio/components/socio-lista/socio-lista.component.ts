import { SocioComponent } from './../../containers/socio/socio.component';
import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Socio } from '../../model/socio';
import { SocioService } from '../../services/socio.service';
import { Router } from '@angular/router';

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

  constructor(private servico: SocioService,
    private router: Router) {}

  readonly colunasExibidas = [
    'numeroInscricao',
    'nome',
    'dataNascimento',
    'sexo',
    'estahAtivo',
    'acoes',
  ];

  ngOnInit(): void { }

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

}
