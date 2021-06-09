package com.example.application.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @NotNull
    @NotEmpty
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

    public Prestamo() {
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Duracion getDuracion() {
        return duracion;
    }

    public void setDuracion(Duracion duracion) {
        this.duracion = duracion;
    }

    public Tipo getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(Tipo tipoInteres) {
        this.tipoInteres = tipoInteres;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
