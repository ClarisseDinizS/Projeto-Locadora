import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AtorService } from '../../services/ator.service';

@Component({
  selector: 'app-ator-formulario',
  templateUrl: './ator-formulario.component.html',
  styleUrls: ['./ator-formulario.component.scss'],
})
export class AtorFormularioComponent {
  formulario = this.formBuild.group({
    nome: ['']
  });

  constructor(private formBuild: NonNullableFormBuilder,
    private servico: AtorService,
    private snackBar: MatSnackBar,
    private localizacao: Location) { }

  ngOnInit(): void { }

  onSubmit() {
    this.servico.salvar(this.formulario.value).subscribe(
      (resultado) => this.onSucess(),
      (erro) => this.onError()
    );
  }

  onCancel() {
    this.localizacao.back();
  }

  private onSucess() {
    this.snackBar.open('Ator salvo com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Error ao salvar ator.', '', { duration: 5000 });
  }
}
