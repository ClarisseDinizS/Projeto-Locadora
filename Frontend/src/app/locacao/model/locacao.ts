import { Item } from "src/app/item/model/item";
import { Cliente } from "src/app/socio/model/cliente";

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
