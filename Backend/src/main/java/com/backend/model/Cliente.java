package com.backend.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer numeroInscricao;

    @Column(length = 200, nullable = false)
    private String nome;

    @Column
    private LocalDate dataNascimento;

    @Column(length = 15, nullable = false)
    private String sexo;

    @Column(length = 5, nullable = false)
    private String estahAtivo;
}
