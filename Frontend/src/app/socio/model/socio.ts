import { Cliente } from './cliente';

export interface Socio {
  id: number;
  numeroInscricao: number;
  nome: string;
  dataNascimento: Date;
  sexo: string;
  estahAtivo: string;
  cpf: string;
  endereco: string;
  telefone: string;
  dependentes: Cliente[];
}
