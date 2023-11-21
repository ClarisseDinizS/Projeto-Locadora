import { Locacao } from './../../model/locacao';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Item } from '../../../item/model/item';
import { ItemService } from '../../../item/services/item.service';
import { SocioService } from '../../../socio/services/socio.service';
import { LocacaoService } from '../../service/locacao.service';
import { Cliente } from '../../../socio/model/cliente';

@Component({
  selector: 'app-locacao-formulario',
  templateUrl: './locacao-formulario.component.html',
  styleUrls: ['./locacao-formulario.component.scss'],
})
export class LocacaoFormularioComponent implements OnInit {

  formulario!: FormGroup;
  // formulario = this.formBuild.group({
  //   id: [0],
  //   dtLocacao: [new Date(), Validators.required],
  //   dtDevolucaoPrevista: [new Date(), Validators.required],
  //   dtDevolucaoEfetiva: [new Date(), Validators.required],
  //   valorCobrado: [0],
  //   multaCobrada: [0],
  //   item: [<Item>{}],
  //   cliente: [<Cliente>{}],
  // });

  itens: Item[] = [];
  clientes: Cliente[] = [];

  compareWithItem = (item1: Item, item2: Item) => item1.id == item2.id;
  compareWithCliente = (cliente1: Cliente, cliente2: Cliente) =>
    cliente1.id == cliente2.id;

  constructor(
    private formBuild: NonNullableFormBuilder,
    private servico: LocacaoService,
    private itemService: ItemService,
    private clienteService: SocioService,
    private snackBar: MatSnackBar,
    private localizacao: Location,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.itemService.listar().subscribe((itens) => {
      this.itens = itens;
    });

    this.clienteService.listarClientes().subscribe((clientes) => {
      this.clientes = clientes;
    });

      const locacao: Locacao =
      this.route.snapshot.data['locacao'] ||
      ({
        id: 0,
        dtLocacao: new Date(),
        dtDevolucaoPrevista: new Date(),
        dtDevolucaoEfetiva: new Date(),
        valorCobrado: 0,
        multaCobrada: 0,
        item: <Item>{},
        cliente: <Cliente>{},
      } as Locacao);

    this.formulario = this.formBuild.group({
      id: [locacao.id],
      dtLocacao: [locacao.dtLocacao, Validators.required],
      dtDevolucaoPrevista: [locacao.dtDevolucaoPrevista, Validators.required],
      dtDevolucaoEfetiva: [locacao.dtDevolucaoEfetiva, Validators.required],
      valorCobrado: [locacao.valorCobrado],
      multaCobrada: [locacao.multaCobrada],
      item: [locacao.item, Validators.required],
      cliente: [locacao.cliente, Validators.required],
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
