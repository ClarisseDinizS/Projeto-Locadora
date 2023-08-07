package com.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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
