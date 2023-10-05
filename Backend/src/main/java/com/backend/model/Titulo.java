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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "titulo")
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
    private String nome;

    @Column
    private LocalDate ano;

    @Column(length = 200, nullable = false)
    private String sinopse;

    @NotNull
    @Column
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "idDiretor")
    private Diretor diretor;

    @ManyToOne
    @JoinColumn(name = "idClasse")
    private Classe classe;

    @ManyToMany
    @NotNull
    @JoinTable(name = "ator_titulo", 
            joinColumns = { @JoinColumn(name = "idTitulo") }, 
            inverseJoinColumns = { @JoinColumn(name = "idAtor") })
    private List<Ator> atores;
}