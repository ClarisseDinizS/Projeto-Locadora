import {Component, OnInit} from '@angular/core';
import {NonNullableFormBuilder, Validators} from "@angular/forms";
import {DiretorService} from "../../../diretor/service/diretor.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {Diretor} from "../../../diretor/model/diretor";
import {Titulo} from "../../../titulo/model/titulo";
import {Item} from "../../../item/model/item";
import {Socio} from "../../../socio/model/socio";
import {TituloService} from "../../../titulo/services/titulo.service";
import {ItemService} from "../../../item/services/item.service";
import {SocioService} from "../../../socio/services/socio.service";
import {Locacao} from "../../model/locacao";

@Component({
  selector: 'app-locacao-formulario',
  templateUrl: './locacao-formulario.component.html',
  styleUrls: ['./locacao-formulario.component.scss']
})
export class LocacaoFormularioComponent implements OnInit {

  formulario = this.formBuild.group({
    id: [0],
    dtLocacao: [new Date(), Validators.required],
    dtDevolucaoPrevista: [new Date(), Validators.required],
    dtDevolucaoEfetiva: [new Date(), Validators.required],
    valorCobrado: [0],
    multaCobrada: [0],
    item: [<Item>{}],
    socio: [<Socio>{}]

  });

  itens: Item[] = [];
  socios: Socio[] = [];

  compareWithItem=(item1: Item, item2: Item) => item1.id == item2.id;
  compareWithSocio=(socio1: Socio, socio2: Socio) => socio1.id == socio2.id;

  constructor(private formBuild: NonNullableFormBuilder,
              private servico: DiretorService,
              private itemService: ItemService,
              private socioService: SocioService,
              private snackBar: MatSnackBar,
              private localizacao: Location,
              private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.itemService.listar().subscribe((itens) => {
      this.itens = itens;
    });

    this.socioService.listar().subscribe((socios) => {
      this.socios = socios;
    });

    const locacao: Locacao = this.route.snapshot.data['locacao'];

    this.formulario.setValue({
      id: locacao.id,
      dtLocacao: locacao.dtLocacao,
      dtDevolucaoPrevista: locacao.dtDevolucaoPrevista,
      dtDevolucaoEfetiva: locacao.dtDevolucaoEfetiva,
      valorCobrado: locacao.valorCobrado,
      multaCobrada: locacao.multaCobrada,
      item: locacao.item,
      socio: locacao.socio
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
    this.snackBar.open('Locação salva com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Error ao salvar locação.', '', { duration: 5000 });
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
