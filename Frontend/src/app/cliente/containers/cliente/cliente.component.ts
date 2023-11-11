import { Component } from '@angular/core';
import {catchError, Observable, of} from "rxjs";
import {MatDialog} from "@angular/material/dialog";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ErrorDialogComponent} from "../../../shared/components/error-dialog/error-dialog.component";
import {ConfirmationDialogComponent} from "../../../shared/components/confirmation-dialog/confirmation-dialog.component";
import {Cliente} from "../../model/cliente";
import {ClienteService} from "../../services/cliente.service";

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class ClienteComponent {
  clientes$: Observable<Cliente[]> | null = null;

  constructor(
    private clienteServico: ClienteService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.recarregar();
  }

  recarregar() {
    this.clientes$ = this.clienteServico.listar().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar clientes');
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

  onEdit(cliente: Cliente) {
    this.router.navigate(['editar', cliente.id], { relativeTo: this.route });
  }

  onRemove(cliente: Cliente) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja excluir esse cliente?',
    });

    dialogRef.afterClosed().subscribe((resultado: boolean) => {
      if (resultado) {
        this.clienteServico.excluir(cliente.id).subscribe(
          () => {
            this.recarregar();
            this.snackBar.open('Cliente removido com sucesso!', 'X', {
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center',
            });
          },
          (error) => {
            this.onError(
              error.error ? error.error : 'Erro ao tentar remover o cliente.'
            )}
        );
      }
    });
  }
}
