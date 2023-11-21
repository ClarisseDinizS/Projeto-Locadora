package com.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.backend.dto.LocacaoDTO;
import com.backend.dto.mapper.LocacaoMapper;
import com.backend.exception.RegistroNotFoundException;
import com.backend.repository.LocacaoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class LocacaoService {
    private final LocacaoRepository locacaoRepository;
    private final LocacaoMapper locacaoMapper;

    public LocacaoService(LocacaoRepository locacaoRepository, LocacaoMapper locacaoMapper) {
        this.locacaoRepository = locacaoRepository;
        this.locacaoMapper = locacaoMapper;
    }

    public List<LocacaoDTO> listar() {
        return locacaoRepository.findAll().stream().map(locacaoMapper::paraDTO).collect(Collectors.toList());
    }

    public LocacaoDTO buscarPorId(@NotNull @Positive Long id) {
        return locacaoRepository.findById(id).map(locacaoMapper::paraDTO)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public LocacaoDTO criar(@Valid @NotNull LocacaoDTO locacaoDto) {
        return locacaoMapper.paraDTO(locacaoRepository.save(locacaoMapper.paraEntidade(locacaoDto)));
    }

    public LocacaoDTO atualizar(@NotNull @Positive Long id, @Valid LocacaoDTO locacaoDto) {
        return locacaoRepository.findById(id)
                .map(registro -> {
                    registro.setDtLocacao(locacaoDto.dtLocacao());
                    registro.setDtDevolucaoPrevista(locacaoDto.dtDevolucaoPrevista());
                    registro.setDtDevolucaoEfetiva(locacaoDto.dtDevolucaoEfetiva());
                    registro.setValorCobrado(locacaoDto.valorCobrado());
                    registro.setMultaCobrada(locacaoDto.multaCobrada());
                    registro.setItem(locacaoDto.item());
                    registro.setCliente(locacaoDto.cliente());

                    return locacaoMapper.paraDTO(locacaoRepository.save(registro));
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        locacaoRepository.delete(locacaoRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }
}
