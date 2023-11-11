import { Component } from '@angular/core';
import {NonNullableFormBuilder, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {ClienteService} from "../../services/cliente.service";
import {Cliente} from "../../model/cliente";

@Component({
  selector: 'app-cliente-formulario',
  templateUrl: './cliente-formulario.component.html',
  styleUrls: ['./cliente-formulario.component.scss']
})
export class ClienteFormularioComponent {
  formulario = this.formBuild.group({
    id: [0],
    numeroInscricao: [0],
    nome: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    dataNascimento: [new Date, Validators.required],
    sexo: ['', Validators.required],
    estahAtivo: ['', Validators.required],
  });

  constructor(private formBuild: NonNullableFormBuilder,
              private servico: ClienteService,
              private snackBar: MatSnackBar,
              private localizacao: Location,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    const cliente: Cliente = this.route.snapshot.data['cliente'];
    this.formulario.setValue({
      id: cliente.id,
      numeroInscricao: cliente.numeroInscricao,
      nome: cliente.nome,
      dataNascimento: cliente.dataNascimento,
      sexo: cliente.sexo,
      estahAtivo: cliente.estahAtivo

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
    this.snackBar.open('Cliente salvo com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Error ao salvar cliente.', '', { duration: 5000 });
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
