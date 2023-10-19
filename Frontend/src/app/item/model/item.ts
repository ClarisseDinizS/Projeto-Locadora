import { Titulo } from "src/app/titulo/model/titulo";

export interface Item {
  id: number;
  numSerie: number;
  dtaAquisicao: Date;
  tipoItem: string;
  titulo: Titulo;
}
