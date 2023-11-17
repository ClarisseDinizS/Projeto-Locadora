package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table (name = "dependente")
@EqualsAndHashCode(callSuper = true)
public class Dependente extends Cliente {

    @JsonIgnoreProperties("dependentes")
    @ManyToOne
    @JoinColumn(name = "socio_id")
    private Socio socio;

}
