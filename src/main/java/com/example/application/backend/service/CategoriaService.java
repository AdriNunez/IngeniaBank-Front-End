package com.example.application.backend.service;

import com.example.application.backend.model.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> findAll();

    Categoria findById(Long id);

    Categoria saveCuenta(Categoria categoria);

    void deleteById(Long id);
}
