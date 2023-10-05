package com.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer numeroInscricao;

    @Column(length = 200, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false)
    private String cpf;

    @Column
    private LocalDate dataNascimento;

    @Column(length = 200, nullable = false)
    private String endereco;

    @Column(length = 20, nullable = false)
    private String telefone;

    @Column(length = 15, nullable = false)
    private String sexo;

    @OneToMany
    @JoinTable(name = "socio_dependente", 
            joinColumns = { @JoinColumn(name = "idSocio") }, 
            inverseJoinColumns = { @JoinColumn(name = "idCliente") })
    private List<Cliente> dependentes;

    @Column(length = 5, nullable = false)
    private String estahAtivo;
}
