import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Diretor } from '../../model/diretor';
import { DiretorService } from '../../service/diretor.service';

@Component({
  selector: 'app-diretor',
  templateUrl: './diretor.component.html',
  styleUrls: ['./diretor.component.scss'],
})
export class DiretorComponent implements OnInit {

  diretores$: Observable<Diretor[]> | null = null;

  constructor(
    private diretorServico: DiretorService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.recarregar();
  }

  ngOnInit(): void { }

  recarregar() {
    this.diretores$ = this.diretorServico.listar().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar diretores.');
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

  onEdit(diretor: Diretor) {
    this.router.navigate(['editar', diretor.id], { relativeTo: this.route });
  }

  onRemove(diretor: Diretor) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja excluir esse diretor?',
    });

    dialogRef.afterClosed().subscribe((resultado: boolean) => {
      if (resultado) {
        this.diretorServico.excluir(diretor.id).subscribe(
          () => {
            this.recarregar();
            this.snackBar.open('Diretor removido com sucesso!', 'X', {
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center',
            });
          },
          (error) => {
            this.onError(
              error.error ? error.error : 'Erro ao tentar remover o título.'
            )}
        );
      }
    });
  }
}
