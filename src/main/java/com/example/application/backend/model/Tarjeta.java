package com.example.application.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Tarjeta extends AbstractEntity{


    @Column(name="numero_tarjeta")
    private Long numeroTarjeta;

    private Integer idMasked;

    @Column(name="ccv"  , nullable = false)
    private Long ccv;

    @Column(name="fecha_expedicion" , nullable = false)
    private LocalDateTime fechaExpedicion;

    @Column(name="fecha_expiracion" , nullable = false)
    private LocalDateTime fechaExpiracion;

    @Column(name="tipo" , nullable = false)
    private String tipo;

    @Column(name="estado_tarjeta" , nullable = false)
    private Estado estadoTarjeta;

    @Column(name="limite")
    private Integer limite;

    @ManyToOne()
    @JoinColumn(name ="cuenta_id")
    @JsonIgnore
    private Cuenta cuenta;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tarjeta", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Movimiento> listaMovimientos;

    public Tarjeta() {
    }

    public Tarjeta(Integer idMasked,Long ccv, LocalDateTime fechaExpedicion, LocalDateTime fechaExpiracion, String tipo, Estado estadoTarjeta, Integer limite) {
        this.idMasked = idMasked;
        this.ccv = ccv;
        this.fechaExpedicion = fechaExpedicion;
        this.fechaExpiracion = fechaExpiracion;
        this.tipo = tipo;
        this.estadoTarjeta = estadoTarjeta;
        this.limite = limite;
    }

    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public Long getCcv() {
        return ccv;
    }

    public void setCcv(Long ccv) {
        this.ccv = ccv;
    }

    public LocalDateTime getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(LocalDateTime fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Estado getEstadoTarjeta() {
        return estadoTarjeta;
    }

    public void setEstadoTarjeta(Estado estadoTarjeta) {
        this.estadoTarjeta = estadoTarjeta;
    }

    public Integer getLimite() {
        return limite;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    public String getMaskedNumeroTarjeta(){
        return hideCardNumber(numeroTarjeta.toString());
    }

    private String hideCardNumber(String cardNumber){
        return "**** " + Integer.parseInt(cardNumber.substring(cardNumber.length() - 4));
    }


    @Override
    public String toString() {
        return "Tarjeta{" +
                "numeroTarjeta=" + numeroTarjeta +
                ", ccv=" + ccv +
                ", fechaExpedicion=" + fechaExpedicion +
                ", fechaExpiracion=" + fechaExpiracion +
                ", tipo='" + tipo + '\'' +
                ", estadoTarjeta='" + estadoTarjeta + '\'' +
                ", limite=" + limite +
                '}';
    }
}
