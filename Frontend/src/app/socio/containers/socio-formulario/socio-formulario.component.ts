import { Component } from '@angular/core';
import {
  FormGroup,
  NonNullableFormBuilder,
  UntypedFormArray,
  Validators,
} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { SocioService } from '../../services/socio.service';
import { Socio } from '../../model/socio';
import { Cliente } from '../../model/cliente';
import { FormUtilsService } from 'src/app/shared/form/form-utils.service';

@Component({
  selector: 'app-socio-formulario',
  templateUrl: './socio-formulario.component.html',
  styleUrls: ['./socio-formulario.component.scss'],
})
export class SocioFormularioComponent {

  formulario!: FormGroup;

  constructor(
    private formBuild: NonNullableFormBuilder,
    private servico: SocioService,
    private snackBar: MatSnackBar,
    private localizacao: Location,
    private route: ActivatedRoute,
    public formUtils: FormUtilsService
  ) { }

  ngOnInit(): void {
    const socio: Socio =
      this.route.snapshot.data['socio'] ||
      ({
        id: 0,
        nome: '',
        dataNascimento: new Date(),
        sexo: '',
        estahAtivo: 'Sim',
        cpf: '',
        endereco: '',
        telefone: '',
        dependentes: [],
      } as Socio);

    this.formulario = this.formBuild.group({
      id: [socio.id],
      nome: [
        socio.nome,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(100),
        ],
      ],
      dataNascimento: [socio.dataNascimento, Validators.required],
      sexo: [socio.sexo, Validators.required],
      estahAtivo: [socio.estahAtivo, Validators.required],
      cpf: [
        socio.cpf,
        [
          Validators.required,
          Validators.minLength(11),
          Validators.maxLength(11),
        ],
      ],
      endereco: [
        socio.endereco,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(200),
        ],
      ],
      telefone: [
        socio.telefone,
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(11),
        ],
      ],
      dependentes: this.formBuild.array(this.obterDependentes(socio)),
    });
  }

  private obterDependentes(socio: Socio) {
    const dependentes = [];
    if (socio?.dependentes) {
      socio.dependentes.forEach((dependente) => {
        dependentes.push(
          this.criarDependente(dependente)
        );
      });
    } else {
      dependentes.push(this.criarDependente());
    }
    return dependentes;
  }

  private criarDependente(
    dependente: Cliente = {
      id: 0,
      nome: '',
      dataNascimento: new Date(),
      sexo: '',
      estahAtivo: 'Sim',
    }
  ) {
    return this.formBuild.group({
      id: [dependente.id],
      nome: [
        dependente.nome,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(100),
        ],
      ],
      dataNascimento: [dependente.dataNascimento, Validators.required],
      sexo: [dependente.sexo, Validators.required],
      estahAtivo: [dependente.estahAtivo, Validators.required],
    });
  }

  obterDependenteFormArray() {
    return (<UntypedFormArray>this.formulario.get('dependentes')).controls;
  }

  adicionarNovoDependente() {
    const dependentesFormArray = <UntypedFormArray>this.formulario.get('dependentes');

    const maxDependentes = 3;

    if (dependentesFormArray.length < maxDependentes) {
      dependentesFormArray.push(this.criarDependente());
    }
  }

  removerDependente(index: number) {
    console.log(this.formulario.get('dependentes'));
    (<UntypedFormArray>this.formulario.get('dependentes')).removeAt(index);
  }

  onSubmit() {
    console.log(this.formulario.value);
    if (this.formulario.valid) {
      this.servico.salvar(this.formulario.value).subscribe(
        (resultado) => this.onSucess(),
        (erro) => this.onError()
      );
    } else {
      this.formUtils.validateAllFormFields(this.formulario);
    }
  }

  onCancel() {
    this.localizacao.back();
  }

  private onSucess() {
    this.snackBar.open('Sócio salvo com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Error ao salvar sócio.', '', { duration: 5000 });
  }
}
