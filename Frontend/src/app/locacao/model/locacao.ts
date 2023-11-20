import {Item} from "../../item/model/item";
import {Cliente} from "../../socio/model/cliente";
import {Socio} from "../../socio/model/socio";

export interface Locacao {
  id: number;
  dtLocacao: Date;
  dtDevolucaoPrevista: Date;
  dtDevolucaoEfetiva: Date;
  valorCobrado: number;
  multaCobrada: number;
  item: Item;
  socio: Socio;
}
