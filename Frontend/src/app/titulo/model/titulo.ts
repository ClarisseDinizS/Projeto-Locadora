import { Ator } from "../../ator/model/ator";
import { Classe } from "../../classe/model/classe";
import { Diretor } from "../../diretor/model/diretor";

export interface Titulo {
    id: number;
    nome: string;
    ano: number;
    sinopse: string;
    categoria: string;
    diretor: Diretor;
    classe: Classe;
    atores: Ator[];
}
