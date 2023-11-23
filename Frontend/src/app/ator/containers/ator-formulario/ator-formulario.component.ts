import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { FormUtilsService } from 'src/app/shared/form/form-utils.service';

import { Ator } from '../../model/ator';
import { AtorService } from '../../services/ator.service';

@Component({
  selector: 'app-ator-formulario',
  templateUrl: './ator-formulario.component.html',
  styleUrls: ['./ator-formulario.component.scss'],
})
export class AtorFormularioComponent implements OnInit {

  formulario!: FormGroup;

  constructor(
    private formBuild: NonNullableFormBuilder,
    private servico: AtorService,
    private snackBar: MatSnackBar,
    private localizacao: Location,
    private route: ActivatedRoute,
    public formUtils: FormUtilsService
  ) { }

  ngOnInit(): void {
    const ator: Ator =
      this.route.snapshot.data['ator'] ||
      ({
        id: 0,
        nome: '',
      } as Ator);

    this.formulario = this.formBuild.group({
      id: [ator.id],
      nome: [
        ator.nome,
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
    this.snackBar.open('Ator salvo com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Error ao salvar ator.', '', { duration: 5000 });
  }
}
