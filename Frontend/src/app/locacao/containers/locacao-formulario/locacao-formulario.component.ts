import { Component } from '@angular/core';
import {
  FormGroup,
  NonNullableFormBuilder,
  Validators,
} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Locacao } from '../../model/locacao';
import { FormUtilsService } from 'src/app/shared/form/form-utils.service';
import { Item } from 'src/app/item/model/item';
import { Cliente } from 'src/app/socio/model/cliente';
import { LocacaoService } from '../../services/locacao.service';
import { ItemService } from 'src/app/item/services/item.service';
import { SocioService } from 'src/app/socio/services/socio.service';

@Component({
  selector: 'app-locacao-formulario',
  templateUrl: './locacao-formulario.component.html',
  styleUrls: ['./locacao-formulario.component.scss'],
})
export class LocacaoFormularioComponent {

  formulario!: FormGroup;

  itens: Item[] = [];
  clientes: Cliente[] = [];

  compareWithItem = (item1: Item, item2: Item) => item1.id == item2.id;
  compareWithCliente = (cliente1: Cliente, cliente2: Cliente) =>
    cliente1.id == cliente2.id;

  constructor(
    private formBuild: NonNullableFormBuilder,
    private servico: LocacaoService,
    private clienteService: SocioService,
    private itemService: ItemService,
    private snackBar: MatSnackBar,
    private localizacao: Location,
    private route: ActivatedRoute,
    public formUtils: FormUtilsService
  ) { }

  ngOnInit(): void {
    this.clienteService.listarClientes().subscribe((clientes) => {
      this.clientes = clientes;
    });

    this.itemService.listar().subscribe((itens) => {
      this.itens = itens;
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
      valorCobrado: [locacao.valorCobrado, Validators.required],
      multaCobrada: [locacao.multaCobrada, Validators.required],
      item: [locacao.item, Validators.required],
      cliente: [locacao.cliente, Validators.required],
    });
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
    this.snackBar.open('Locação salva com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Error ao salvar locação.', '', { duration: 5000 });
  }
}
