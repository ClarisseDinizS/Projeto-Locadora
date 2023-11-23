import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { FormUtilsService } from 'src/app/shared/form/form-utils.service';
import { Titulo } from 'src/app/titulo/model/titulo';
import { TituloService } from 'src/app/titulo/services/titulo.service';

import { Item } from '../../model/item';
import { ItemService } from '../../services/item.service';

@Component({
  selector: 'app-item-formulario',
  templateUrl: './item-formulario.component.html',
  styleUrls: ['./item-formulario.component.scss'],
})
export class ItemFormularioComponent implements OnInit {
  formulario!: FormGroup;

  titulos: Titulo[] = [];

  compareWithTitulo = (titulo1: Titulo, titulo2: Titulo) =>
    titulo1.id == titulo2.id;

  constructor(
    private formBuild: NonNullableFormBuilder,
    private servico: ItemService,
    private tituloService: TituloService,
    private snackBar: MatSnackBar,
    private localizacao: Location,
    private route: ActivatedRoute,
    public formUtils: FormUtilsService
  ) { }

  ngOnInit(): void {
    this.tituloService.listar().subscribe((titulos) => {
      this.titulos = titulos;
    });

    const item: Item =
      this.route.snapshot.data['item'] ||
      ({
        id: 0,
        numSerie: 0,
        dtaAquisicao: new Date(),
        tipoItem: '',
        titulo: <Titulo>{},
      } as Item);

    this.formulario = this.formBuild.group({
      id: [item.id],
      numSerie: [item.numSerie, [Validators.required]],
      dtaAquisicao: [item.dtaAquisicao, Validators.required],
      tipoItem: [item.tipoItem, [Validators.required]],
      titulo: [item.titulo],
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
    this.snackBar.open('Item salvo com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Error ao salvar item.', '', { duration: 5000 });
  }
}
