package com.backend.model;

import java.time.LocalDate;

import com.backend.enums.Tipo;
import com.backend.enums.converters.TipoConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer numSerie;

    @Column
    private LocalDate dtaAquisicao;

    @Column(length = 200, nullable = false)
    @Convert(converter = TipoConverter.class)
    private Tipo tipoItem;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "idTitulo")
    private Titulo titulo;
}
