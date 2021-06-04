package com.example.application.backend.dao;

import com.example.application.backend.model.Prestamo;

public interface PrestamoDAO {
    Prestamo findPrestamoById(Long id);
}
