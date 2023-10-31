import { Classe } from './../../../classe/model/classe';
import { Ator } from './../../../ator/model/ator';
import { Diretor } from './../../../diretor/model/diretor';
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Titulo } from '../../model/titulo';
import { TituloService } from '../../services/titulo.service';
import { DiretorService } from 'src/app/diretor/service/diretor.service';
import { AtorService } from 'src/app/ator/services/ator.service';
import { ClasseService } from 'src/app/classe/services/classe.service';

@Component({
  selector: 'app-titulo-formulario',
  templateUrl: './titulo-formulario.component.html',
  styleUrls: ['./titulo-formulario.component.scss'],
})
export class TituloFormularioComponent implements OnInit {
  formulario = this.formBuild.group({
    id: [0],
    nome: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    ano: [new Date, Validators.required],
    sinopse: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    categoria: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    diretor: [<Diretor>{}],
    classe: [<Classe>{}],
    atores: [this.formBuild.array(<Ator[]>[]), Validators.required],
  });

  diretores: Diretor[] = [];
  atores: Ator[] = [];
  classes:Classe[] = [];

  compareWithDiretor=(diretor1: Diretor, diretor2: Diretor) => diretor1.id == diretor2.id;
  compareWithAtor=(ator1: Ator, ator2: Ator) => ator1.id == ator2.id;
  compareWithClasse=(classe1: Classe, classe2: Classe) => classe1.id == classe2.id;

  constructor(private formBuild: NonNullableFormBuilder,
    private servico: TituloService,
    private diretorService: DiretorService,
    private atorService: AtorService,
    private classeService: ClasseService,
    private snackBar: MatSnackBar,
    private localizacao: Location,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.diretorService.listar().subscribe(diretores =>{
      this.diretores = diretores;
    });

    this.classeService.listar().subscribe(classes =>{
      this.classes = classes;
    });

    this.atorService.listar().subscribe(atores =>{
      this.atores = atores;
    });

    const titulo: Titulo = this.route.snapshot.data['titulo'];

    this.formulario.setValue({
      id: titulo.id,
      nome: titulo.nome,
      ano: titulo.ano,
      sinopse: titulo.sinopse,
      categoria: titulo.categoria,
      diretor: titulo.diretor,
      classe: titulo.classe,
      atores: titulo.atores
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
    this.snackBar.open('Titulo salvo com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Error ao salvar titulo.', '', { duration: 5000 });
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
