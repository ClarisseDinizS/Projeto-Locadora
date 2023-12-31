import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';

import { ConfirmationDialogComponent } from '../../../shared/components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';
import { Locacao } from '../../model/locacao';
import { LocacaoService } from '../../services/locacao.service';

@Component({
  selector: 'app-locacao',
  templateUrl: './locacao.component.html',
  styleUrls: ['./locacao.component.scss'],
})
export class LocacaoComponent implements OnInit {

  locacoes$: Observable<Locacao[]> | null = null;

  constructor(
    private locacaoServico: LocacaoService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.recarregar();
  }

  ngOnInit(): void { }

  recarregar() {
    this.locacoes$ = this.locacaoServico.listar().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar locações');
        return of([]);
      })
    );
  }

  onError(mensagemErro: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: mensagemErro,
    });
  }

  onAdd() {
    this.router.navigate(['novo'], { relativeTo: this.route });
  }

  onEdit(locacao: Locacao) {
    this.router.navigate(['editar', locacao.id], { relativeTo: this.route });
  }

  onRemove(locacao: Locacao) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja excluir essa locação?',
    });

    dialogRef.afterClosed().subscribe((resultado: boolean) => {
      if (resultado) {
        this.locacaoServico.excluir(locacao.id).subscribe(
          () => {
            this.recarregar();
            this.snackBar.open('Locação removida com sucesso!', 'X', {
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center',
            });
          },
          (error) => {
            this.onError(
              error.error ? error.error : 'Erro ao tentar remover a locação.'
            );
          }
        );
      }
    });
  }
}
