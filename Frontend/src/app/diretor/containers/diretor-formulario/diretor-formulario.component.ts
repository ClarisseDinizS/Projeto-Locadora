import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Diretor } from '../../model/diretor';
import { DiretorService } from '../../service/diretor.service';

@Component({
  selector: 'app-diretor-formulario',
  templateUrl: './diretor-formulario.component.html',
  styleUrls: ['./diretor-formulario.component.scss'],
})
export class DiretorFormularioComponent {

  formulario = this.formBuild.group({
    id: [0],
    nome: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]]
  });

  constructor(private formBuild: NonNullableFormBuilder,
    private servico: DiretorService,
    private snackBar: MatSnackBar,
    private localizacao: Location,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    const diretor: Diretor = this.route.snapshot.data['diretor'];
    this.formulario.setValue({
      id: diretor.id,
      nome: diretor.nome
    });
  }

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
    this.snackBar.open('Diretor salvo com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Error ao salvar diretor.', '', { duration: 5000 });
  }

  getErrorMessage(fieldName: string) {
    const field = this.formulario.get(fieldName);

    if (field?.hasError('required')) {
      return 'Campo Obrigatório';
    }

    if (field?.hasError('minlength')) {
      const requiredLength: number = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Tamanho mínimo precisa ser de ${requiredLength} caracteres.`;
    }

    if (field?.hasError('maxlength')) {
      const requiredLength: number = field.errors ? field.errors['maxlength']['requiredLength'] : 200;
      return `Tamanho máximo excedido de ${requiredLength} caracteres.`;
    }

    return 'Campo Inválido';
  }
}
