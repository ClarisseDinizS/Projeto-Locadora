import {Item} from "../../item/model/item";
import {Cliente} from "../../socio/model/cliente";

export interface Locacao {
  id: number;
  dtLocacao: Date;
  dtDevolucaoPrevista: Date;
  dtDevolucaoEfetiva: Date;
  valorCobrado: number;
  multaCobrada: number;
  item: Item;
  cliente: Cliente;
}
