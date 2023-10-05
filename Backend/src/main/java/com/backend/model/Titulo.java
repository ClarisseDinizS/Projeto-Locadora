package com.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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