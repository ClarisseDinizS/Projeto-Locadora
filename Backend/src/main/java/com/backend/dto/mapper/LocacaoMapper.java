package com.backend.dto.mapper;

import org.springframework.stereotype.Component;
import com.backend.dto.LocacaoDTO;
import com.backend.model.Locacao;

@Component
public class LocacaoMapper {

    public LocacaoDTO paraDTO(Locacao locacao) {
        if (locacao == null) {
            return null;
        }
        return new LocacaoDTO(locacao.getId(), locacao.getDtLocacao(), locacao.getDtDevolucaoPrevista(), 
                locacao.getDtDevolucaoPrevista(),locacao.getValorCobrado(), locacao.getMultaCobrada(), 
                locacao.getItem(), locacao.getCliente());
    }

    public Locacao paraEntidade(LocacaoDTO locacaoDTO) {
        if (locacaoDTO == null) {
            return null;
        }

        Locacao locacao = new Locacao();
        if (locacaoDTO.id() != null) {
            locacao.setId(locacaoDTO.id());
        }

        locacao.setDtLocacao(locacaoDTO.dtLocacao());
        locacao.setDtDevolucaoPrevista(locacaoDTO.dtDevolucaoPrevista());
        locacao.setDtDevolucaoEfetiva(locacaoDTO.dtDevolucaoEfetiva());
        locacao.setValorCobrado(locacaoDTO.valorCobrado());
        locacao.setMultaCobrada(locacaoDTO.multaCobrada());
        locacao.setItem(locacaoDTO.item());
        locacao.setCliente(locacaoDTO.cliente());

        return locacao;

    }
    
}
