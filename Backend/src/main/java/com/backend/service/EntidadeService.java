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

        switch (object.getClass().getSimpleName()) {
            case "Classe":
                mensagemErro = "Não é possível excluir esta " + object.getClass().getSimpleName()
                        + " por estar relacionado a esses Títulos:";
                titulos = tituloRepository.findByClasse((Classe) object);
                break;
            case "Diretor":
                mensagemErro = "Não é possível excluir este " + object.getClass().getSimpleName()
                        + " por estar relacionado a esses Títulos:";
                titulos = tituloRepository.findByDiretor((Diretor) object);
                break;
            case "Ator":
                mensagemErro = "Não é possível excluir este " + object.getClass().getSimpleName()
                        + " por estar relacionado a esses Títulos:";
                titulos = tituloRepository.findByAtores((Ator) object);
                break;
            case "Titulo":
                mensagemErro = "Não é possível excluir este Título por estar relacionado a esses Itens:";
                itens = itemRepository.findByTitulo((Titulo) object);
                break;
            default:
                mensagemErro = "Tipo de entidade não suportado: " + object.getClass().getSimpleName();
                break;
        }

        StringBuilder mensagem = new StringBuilder(mensagemErro + "\n");

        if (titulos != null && !titulos.isEmpty()) {
            for (Titulo titulo : titulos) {
                mensagem.append(titulo.getNome());
                mensagem.append("\n");
            }
        }

        if (itens != null && !itens.isEmpty()) {
            for (Item item : itens) {
                mensagem.append(item.getNumserie());
                mensagem.append("\n");
            }
        }

        throw new RelationFoundException(mensagem.toString());

    }
}
