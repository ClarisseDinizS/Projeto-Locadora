package com.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table (name = "socio")
@EqualsAndHashCode(callSuper = true)
public class Socio extends Cliente {

    @Column(length = 20, nullable = false)
    private String cpf;

    @Column(length = 200, nullable = false)
    private String endereco;

    @Column(length = 20, nullable = false)
    private String telefone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "socio_dependente", 
            joinColumns = { @JoinColumn(name = "idSocio") }, 
            inverseJoinColumns = { @JoinColumn(name = "idDependente") })
    private List<Cliente> dependentes = new ArrayList<>();
}
