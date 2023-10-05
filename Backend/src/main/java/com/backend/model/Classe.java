package com.backend.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "classe")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
    private String nome;

    @Column
    private Double valor;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
}
