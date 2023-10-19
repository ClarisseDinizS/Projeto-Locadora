import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Ator } from '../../model/ator';
import { AtorService } from '../../services/ator.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-ator',
  templateUrl: './ator.component.html',
  styleUrls: ['./ator.component.scss'],
})
export class AtorComponent implements OnInit {
  atores$: Observable<Ator[]> | null = null;

  constructor(
    private atorServico: AtorService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.recarregar();
  }

  recarregar() {
    this.atores$ = this.atorServico.listar().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar atores.');
        return of([]);
      })
    );
  }

  onError(mensagemErro: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: mensagemErro,
    });
  }

  ngOnInit(): void { }

  onAdd() {
    this.router.navigate(['novo'], { relativeTo: this.route });
  }

  onEdit(ator: Ator) {
    this.router.navigate(['editar', ator.id], { relativeTo: this.route });
  }

  onRemove(ator: Ator) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja excluir esse ator?',
    });

    dialogRef.afterClosed().subscribe((resultado: boolean) => {
      if (resultado) {
        this.atorServico.excluir(ator.id).subscribe(
          () => {
            this.recarregar();
            this.snackBar.open('Ator removido com sucesso!', 'X', {
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center',
            });
          },
          (error) =>
            this.onError(
              error.error ? error.error : 'Erro ao tentar remover o título.'
            )
        );
      }
    });
  }
}
