package com.backend.dto.mapper;

import org.springframework.stereotype.Component;

import com.backend.dto.ClienteDTO;
import com.backend.dto.ItemDTO;
import com.backend.dto.LocacaoDTO;
import com.backend.model.Cliente;
import com.backend.model.Item;
import com.backend.model.Locacao;

@Component
public class LocacaoMapper {

    private final ClienteMapper clienteMapper;
    private final ItemMapper itemMapper;

    public LocacaoMapper(ClienteMapper clienteMapper, ItemMapper itemMapper) {
        this.clienteMapper = clienteMapper;
        this.itemMapper = itemMapper;
    }

    public ClienteMapper getClienteMapper() {
        return clienteMapper;
    }

    public ItemMapper getItemMapper() {
        return itemMapper;
    }

    public LocacaoDTO paraDTO(Locacao locacao) {
        if (locacao == null) {
            return null;
        }

        ClienteDTO cliente = locacao.getCliente() != null
                ? new ClienteDTO(locacao.getCliente().getId(), locacao.getCliente().getNumeroInscricao(),
                        locacao.getCliente().getNome(), locacao.getCliente().getDataNascimento(),
                        locacao.getCliente().getSexo(), locacao.getCliente().getEstahAtivo().getValor())
                : null;

        ItemDTO item = locacao.getItem() != null
                ? new ItemDTO(locacao.getItem().getId(), locacao.getItem().getNumSerie(),
                        locacao.getItem().getDtaAquisicao(), locacao.getItem().getTipoItem().getValor(),
                        locacao.getItem().getTitulo())
                : null;

        return new LocacaoDTO(locacao.getId(), locacao.getDtLocacao(), locacao.getDtDevolucaoPrevista(),
                locacao.getDtDevolucaoPrevista(), locacao.getValorCobrado(), locacao.getMultaCobrada(),
                item, cliente);
    }

    public Locacao paraEntidade(LocacaoDTO locacaoDTO) {
        if (locacaoDTO == null) {
            return null;
        }

        Locacao locacao = new Locacao();
        if (locacaoDTO.id() != null) {
            locacao.setId(locacaoDTO.id());
        }

        Cliente cliente = locacaoDTO.cliente() != null ? new Cliente() : null;

        cliente.setId(locacaoDTO.cliente().id());
        cliente.setNumeroInscricao(locacaoDTO.cliente().numeroInscricao());
        cliente.setNome(locacaoDTO.cliente().nome());
        cliente.setDataNascimento(locacaoDTO.cliente().dataNascimento());
        cliente.setSexo(locacaoDTO.cliente().sexo());
        cliente.setEstahAtivo(this.clienteMapper.converterValorEstahAtivo(locacaoDTO.cliente().estahAtivo()));

        Item item = locacaoDTO.item() != null
                ? new Item() : null;

        item.setId(locacaoDTO.item().id());
        item.setNumSerie(locacaoDTO.item().numSerie());
        item.setDtaAquisicao(locacaoDTO.item().dtaAquisicao());
        item.setTipoItem(this.itemMapper.converterTipoItem(locacaoDTO.item().tipoItem()));
        item.setTitulo(locacaoDTO.item().titulo());

        locacao.setDtLocacao(locacaoDTO.dtLocacao());
        locacao.setDtDevolucaoPrevista(locacaoDTO.dtDevolucaoPrevista());
        locacao.setDtDevolucaoEfetiva(locacaoDTO.dtDevolucaoEfetiva());
        locacao.setValorCobrado(locacaoDTO.valorCobrado());
        locacao.setMultaCobrada(locacaoDTO.multaCobrada());
        locacao.setItem(item);
        locacao.setCliente(cliente);

        return locacao;

    }

}
