package com.example.application.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="prestamo")
public class Prestamo extends  AbstractEntity{

    @NotNull
    @Column(name="cantidad" , nullable = false)
    private Double cantidad;


    @Column(name="duracion" , nullable = false)
    private Duracion duracion;


    @Column(name="tipo_interes" , nullable = false)
    private Tipo tipoInteres;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "prestamo_cuentas",
            joinColumns = {@JoinColumn(name="prestamo_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="cuenta_id", referencedColumnName = "id")}
    )

    private List<Cuenta> cuentas = new ArrayList<>();

}
