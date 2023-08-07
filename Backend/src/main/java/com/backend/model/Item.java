package com.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer numserie;

    @Column
    private LocalDate dtaquisicao;

    @Column(length = 200, nullable = false)
    private String tipoItem;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "idTitulo")
    private Titulo titulo;

}
