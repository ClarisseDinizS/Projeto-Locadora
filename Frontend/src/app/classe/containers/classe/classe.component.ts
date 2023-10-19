import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Classe } from '../../model/classe';
import { ClasseService } from '../../services/classe.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-classe',
  templateUrl: './classe.component.html',
  styleUrls: ['./classe.component.scss'],
})
export class ClasseComponent implements OnInit {
  classes$: Observable<Classe[]> | null = null;

  constructor(
    private classeServico: ClasseService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.recarregar();
  }

  recarregar() {
    this.classes$ = this.classeServico.listar().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar classes.');
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

  onEdit(classe: Classe) {
    this.router.navigate(['editar', classe.id], { relativeTo: this.route });
  }

  onRemove(classe: Classe) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja excluir esse classe?',
    });

    dialogRef.afterClosed().subscribe((resultado: boolean) => {
      if (resultado) {
        this.classeServico.excluir(classe.id).subscribe(
          () => {
            this.recarregar();
            this.snackBar.open('Classe removido com sucesso!', 'X', {
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
