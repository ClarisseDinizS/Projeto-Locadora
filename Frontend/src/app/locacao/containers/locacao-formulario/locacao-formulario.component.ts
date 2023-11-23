import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/item/model/item';
import { ItemService } from 'src/app/item/services/item.service';
import { FormUtilsService } from 'src/app/shared/form/form-utils.service';
import { Cliente } from 'src/app/socio/model/cliente';
import { SocioService } from 'src/app/socio/services/socio.service';

import { Classe } from '../../../classe/model/classe';
import { ClasseService } from '../../../classe/services/classe.service';
import { Locacao } from '../../model/locacao';
import { LocacaoService } from '../../services/locacao.service';

@Component({
  selector: 'app-locacao-formulario',
  templateUrl: './locacao-formulario.component.html',
  styleUrls: ['./locacao-formulario.component.scss'],
})
export class LocacaoFormularioComponent implements OnInit {

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
    private classeService: ClasseService,
    private itemService: ItemService,
    private snackBar: MatSnackBar,
    private localizacao: Location,
    private route: ActivatedRoute,
    public formUtils: FormUtilsService,
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
        item: <Item>{},
        cliente: <Cliente>{},
        valorCobrado: 0,
        multaCobrada: 0,

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
    if (this.formulario.valid) {
      this.servico.salvar(this.formulario.value).subscribe(
        (resultado) => this.onSucess(),
        (erro) => this.onError()
      );
    } else {
      this.formUtils.validateAllFormFields(this.formulario);
    }
  }

  atualizarValorCobrado() {
    const itemSelecionado: (Item)= this.formulario.get('item')?.value;

    this.classeService.buscarPorId(itemSelecionado.titulo.classe.id).subscribe(
      (classe: Classe) => {
        // Agora, atualize o valor cobrado no formulário
        this.formulario.get('valorCobrado')?.setValue(classe.valor);
      },
      (erro) => {
        console.error('Erro ao buscar classe por ID:', erro);
      }
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
}
