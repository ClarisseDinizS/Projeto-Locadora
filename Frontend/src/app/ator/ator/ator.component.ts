import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Ator } from '../model/ator';
import { AtorService } from '../services/ator.service';

@Component({
  selector: 'app-ator',
  templateUrl: './ator.component.html',
  styleUrls: ['./ator.component.scss'],
})
export class AtorComponent implements OnInit {
  atores$: Observable<Ator[]>;
  colunasExibidas = ['id', 'nome', 'acoes'];

  constructor(
    private atorServico: AtorService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.atores$ = this.atorServico.list().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar atores');
        return of([]);
      })
    );
  }

  onError(message: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: message,
    });
  }

  ngOnInit(): void { }

  onAdd() {
    this.router.navigate(['novo'], { relativeTo: this.route });
  }
}
