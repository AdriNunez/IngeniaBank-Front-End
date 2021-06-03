package com.example.application.backend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="cantidad" , nullable = false)
    private Double cantidad;

    @Column(name="duracion" , nullable = false)
    private Duracion duracion;

    @Column(name="tipo_interes" , nullable = false)
    private Tipo tipoInteres;

   /*RELACIONES A AMBAS CUENTAS DE TIPO CUENTAS*/

}
