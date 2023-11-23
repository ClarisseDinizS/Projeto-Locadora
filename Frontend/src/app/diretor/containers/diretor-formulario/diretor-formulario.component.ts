import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { FormUtilsService } from 'src/app/shared/form/form-utils.service';

import { Diretor } from '../../model/diretor';
import { DiretorService } from '../../service/diretor.service';

@Component({
  selector: 'app-diretor-formulario',
  templateUrl: './diretor-formulario.component.html',
  styleUrls: ['./diretor-formulario.component.scss'],
})
export class DiretorFormularioComponent implements OnInit {
  formulario!: FormGroup;

  constructor(
    private formBuild: NonNullableFormBuilder,
    private servico: DiretorService,
    private snackBar: MatSnackBar,
    private localizacao: Location,
    private route: ActivatedRoute,
    public formUtils: FormUtilsService
  ) { }

  ngOnInit(): void {
    const diretor: Diretor =
      this.route.snapshot.data['diretor'] ||
      ({
        id: 0,
        nome: '',
      } as Diretor);

    this.formulario = this.formBuild.group({
      id: [diretor.id],
      nome: [
        diretor.nome,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(100),
        ],
      ],
    });
  }

  onSubmit() {
    if (this.formulario.valid) {
      this.servico.salvar(this.formulario.value).subscribe(
        (resultado) => this.onSucess(),
        (erro) => this.onError()
      );
    } else {
      this.formUtils.validateAllFormFields(this.formulario);
    };
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
}
