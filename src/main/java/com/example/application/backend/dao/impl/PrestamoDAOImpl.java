package com.example.application.backend.dao.impl;

import com.example.application.backend.dao.PrestamoDAO;
import com.example.application.backend.model.Prestamo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

@Repository
public class PrestamoDAOImpl implements PrestamoDAO {


    @PersistenceContext
    private EntityManager manager;
    //Variables
    private Integer duracion;
    private String tiempo;
    private Double cantidad;
    public Prestamo findPrestamoById(Long id){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Prestamo> criteria = builder.createQuery(Prestamo.class);
        Root<Prestamo> root =     criteria.from(Prestamo.class);

        criteria.select(root);
        criteria.where(builder.equal(root.get("id"), id));
        return manager.createQuery(criteria).getSingleResult();
    }

    /**
     * Calculo de la cantidadMensual
     * Se realiza el cálculo sencillo
     * Proxima implementación
     * cantidad mensual = Cantidad * i/1-(1+i) exp -N
     * @param cantidad
     * @param tiempo
     * @param duracion
     * @return
     */
    @Override
    public Double cantidadMensual(Double cantidad, String tiempo, Integer duracion) {


        Double cantidadMensual = 0D;
        Integer tiempoM = 0;

//        double  exp=0.0;
//        Double i1 = 0D; //tanto por uno
//        i1 = 5d/100 ;
//        Double inte = i1;
        Double E ;
        Double Ei ;
        int indice;
        this.cantidad = cantidad;
        this.tiempo = tiempo;
        this.duracion = duracion;
        switch(this.tiempo){
            case "A":
            {
               //paso el año a 12 meses
                    tiempoM  = this.duracion* 12;
                    E =this.cantidad/tiempoM;
                    //cuantía en base al interes (es fijado)
                    Ei= (this.cantidad * 0.05)/12 ;
                    cantidadMensual= E + Ei;

             }break;
            case "M":
            {// Cálculo de la cuota mensual.
                tiempoM  = this.duracion;
                E = this.cantidad/tiempoM;
                //cuantía en base al interes (es fijado)
                Ei= (this.cantidad * 0.05)/12 ;
                cantidadMensual= E + Ei;

            } break;
        }

        return Double.valueOf(Math.round(cantidadMensual));
    }

}
