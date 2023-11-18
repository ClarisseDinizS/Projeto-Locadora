import { Component } from '@angular/core';
import { catchError, Observable, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';
import { ConfirmationDialogComponent } from '../../../shared/components/confirmation-dialog/confirmation-dialog.component';
import { Socio } from '../../model/socio';
import { SocioService } from '../../services/socio.service';

@Component({
  selector: 'app-socio',
  templateUrl: './socio.component.html',
  styleUrls: ['./socio.component.scss'],
})
export class SocioComponent {
  socios$: Observable<Socio[]> | null = null;

  constructor(
    private socioServico: SocioService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.recarregar();
  }

  recarregar() {
    this.socios$ = this.socioServico.listar().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar sócioss');
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
            this.recarregar();
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
