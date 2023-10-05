package com.backend.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

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
