import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';

import { ConfirmationDialogComponent } from '../../../shared/components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';
import { Socio } from '../../model/socio';
import { SocioService } from '../../services/socio.service';

@Component({
  selector: 'app-socio',
  templateUrl: './socio.component.html',
  styleUrls: ['./socio.component.scss'],
})
export class SocioComponent implements OnInit {

  socios$: Observable<Socio[]> | null = null;

  constructor(
    private socioServico: SocioService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.recarregarAtivos();
  }

  ngOnInit(): void { }

  recarregarAtivos() {
    this.socios$ = this.socioServico.listarAtivos().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar sócios ativos');
        return of([]);
      })
    );
  }

  recarregarInativos() {
    this.socios$ = this.socioServico.listarInativos().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar sócios inativos');
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

  onEdit(socio: Socio) {
    this.router.navigate(['editar', socio.id], { relativeTo: this.route });
  }

  onRemove(socio: Socio) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja excluir esse sócio?',
    });

    dialogRef.afterClosed().subscribe((resultado: boolean) => {
      if (resultado) {
        this.socioServico.excluir(socio.id).subscribe(
          () => {
            this.recarregarAtivos();
            this.snackBar.open('Sócio removido com sucesso!', 'X', {
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center',
            });
          },
          (error) => {
            this.onError(
              error.error ? error.error : 'Erro ao tentar remover o sócio.'
            );
          }
        );
      }
    });
  }
}
