import {Diretor} from "../../diretor/model/diretor";
import {Classe} from "../../classe/model/classe";
import {Ator} from "../../ator/model/ator";

export interface Titulo {
    id: number;
    nome: string;
    ano: Date;
    sinopse: string;
    categoria: string;
    diretor?: Diretor;
    classe?: Classe;
    atores?: Ator[];
}
