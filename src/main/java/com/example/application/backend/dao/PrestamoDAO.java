package com.example.application.backend.dao;

import com.example.application.backend.model.Prestamo;

import java.math.BigDecimal;

public interface PrestamoDAO {
    Prestamo findPrestamoById(Long id);

    Double cantidadMensual(Double cantidad, String tiempo, Integer duracion);
}
