import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Classe } from '../../model/classe';
import { ClasseService } from '../../services/classe.service';

@Component({
  selector: 'app-classe-formulario',
  templateUrl: './classe-formulario.component.html',
  styleUrls: ['./classe-formulario.component.scss'],
})
export class ClasseFormularioComponent implements OnInit {
  formulario!: FormGroup;

  constructor(
    private formBuild: NonNullableFormBuilder,
    private servico: ClasseService,
    private snackBar: MatSnackBar,
    private localizacao: Location,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const classe: Classe =
      this.route.snapshot.data['classe'] ||
      ({
        id: 0,
        nome: '',
        valor: 0,
        data: new Date(),
      } as Classe);

    this.formulario = this.formBuild.group({
      id: [classe.id],
      nome: [
        classe.nome,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(100),
        ],
      ],
      valor: [classe.valor, Validators.required],
      data: [classe.data, Validators.required],
    });
  }

  onSubmit() {
    console.log(this.formulario.value);
    this.servico.salvar(this.formulario.value).subscribe(
      (resultado) => this.onSucess(),
      (erro) => this.onError()
    );
  }

  onCancel() {
    this.localizacao.back();
  }

  private onSucess() {
    this.snackBar.open('Classe salva com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Error ao salvar classe.', '', { duration: 5000 });
  }

  getErrorMessage(fieldName: string) {
    const field = this.formulario.get(fieldName);

    if (field?.hasError('required')) {
      return 'Campo Obrigatório';
    }

    if (field?.hasError('minlength')) {
      const requiredLength: number = field.errors
        ? field.errors['minlength']['requiredLength']
        : 5;
      return `Tamanho mínimo precisa ser de ${requiredLength} caracteres.`;
    }

    if (field?.hasError('maxlength')) {
      const requiredLength: number = field.errors
        ? field.errors['maxlength']['requiredLength']
        : 200;
      return `Tamanho máximo excedido de ${requiredLength} caracteres.`;
    }

    return 'Campo Inválido';
  }
}
