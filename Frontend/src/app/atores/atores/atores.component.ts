import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Ator } from '../model/ator';
import { AtoresService } from '../services/atores.service';

@Component({
  selector: 'app-atores',
  templateUrl: './atores.component.html',
  styleUrls: ['./atores.component.scss']
})
export class AtoresComponent implements OnInit {

  atores$: Observable<Ator[]>;
  colunasExibidas = ['id', 'nome'];

  constructor(private atoresServico: AtoresService, public dialog: MatDialog) {
    this.atores$ = this.atoresServico.list()
      .pipe(
        catchError(error => {
          this.onError('Erro ao carregar atores')
          return of([])
        })
      );
    }

    onError(message: string){
      this.dialog.open(ErrorDialogComponent,{
        data: message
      })
    }

    ngOnInit(): void {}
}
