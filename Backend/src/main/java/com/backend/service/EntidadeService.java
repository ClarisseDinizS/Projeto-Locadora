package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.exception.RelationFoundException;
import com.backend.model.Ator;
import com.backend.model.Classe;
import com.backend.model.Diretor;
import com.backend.model.Item;
import com.backend.model.Titulo;
import com.backend.repository.ItemRepository;
import com.backend.repository.TituloRepository;

@Service
public class EntidadeService {

    private final TituloRepository tituloRepository;
    private final ItemRepository itemRepository;

    public EntidadeService(TituloRepository tituloRepository, ItemRepository itemRepository) {
        this.tituloRepository = tituloRepository;
        this.itemRepository = itemRepository;
    }

    public void verificarRelacoes(Object object) {
        List<Titulo> titulos = null;
        List<Item> itens = null;
        String mensagemErro = null;

        if ((object.getClass().getSimpleName()).equals("Classe")
                && !(tituloRepository.findByClasse((Classe) object).isEmpty())) {
            mensagemErro = "Não é possível excluir esta " + object.getClass().getSimpleName()
                    + " por estar relacionado a esses Títulos:\n";
            titulos = tituloRepository.findByClasse((Classe) object);
        } else if ((object.getClass().getSimpleName()).equals("Diretor")
                && !(tituloRepository.findByDiretor((Diretor) object).isEmpty())) {
            mensagemErro = "Não é possível excluir este " + object.getClass().getSimpleName()
                    + " por estar relacionado a esses Títulos:\n";
            titulos = tituloRepository.findByDiretor((Diretor) object);
        } else if ((object.getClass().getSimpleName()).equals("Ator")
                && !(tituloRepository.findByAtores((Ator) object).isEmpty())) {
            mensagemErro = "Não é possível excluir este " + object.getClass().getSimpleName()
                    + " por estar relacionado a esses Títulos:\n";
            titulos = tituloRepository.findByAtores((Ator) object);
        } else if ((object.getClass().getSimpleName()).equals("Titulo")
                && !(itemRepository.findByTitulo((Titulo) object).isEmpty())) {
            mensagemErro = "Não é possível excluir este Título por estar relacionado a esses Itens: ";
            itens = itemRepository.findByTitulo((Titulo) object);
        } else {
            return;
        }

        StringBuilder mensagem = new StringBuilder(mensagemErro);

        if (titulos != null && !titulos.isEmpty()) {
            for (Titulo titulo : titulos) {
                mensagem.append(titulo.getNome());
                mensagem.append("\n");
            }
        }

        if (itens != null && !itens.isEmpty()) {
            for (Item item : itens) {
                mensagem.append(item.getNumSerie());
                mensagem.append(", ");
            }
            mensagem.deleteCharAt(mensagem.length() - 2);
        }

        throw new RelationFoundException(mensagem.toString());
    }
}
