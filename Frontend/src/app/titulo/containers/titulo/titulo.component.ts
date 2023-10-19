import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Titulo } from '../../model/titulo';
import { TituloService } from '../../services/titulo.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-titulo',
  templateUrl: './titulo.component.html',
  styleUrls: ['./titulo.component.scss'],
})
export class TituloComponent implements OnInit {
  titulos$: Observable<Titulo[]> | null = null;

  constructor(
    private tituloServico: TituloService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.recarregar();
  }

  recarregar() {
    this.titulos$ = this.tituloServico.listar().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar titulos.');
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

  onEdit(titulo: Titulo) {
    this.router.navigate(['editar', titulo.id], { relativeTo: this.route });
  }

  onRemove(titulo: Titulo) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja excluir esse titulo?',
    });

    dialogRef.afterClosed().subscribe((resultado: boolean) => {
      if (resultado) {
        this.tituloServico.excluir(titulo.id).subscribe(
          () => {
            this.recarregar();
            this.snackBar.open('Titulo removido com sucesso!', 'X', {
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
