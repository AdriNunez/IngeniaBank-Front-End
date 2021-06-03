package com.example.application.backend.service;

import com.example.application.backend.model.Prestamo;

import java.util.List;
import java.util.Optional;

public interface PrestamoService {

    List<Prestamo> findAll();

    Optional<Prestamo> findById(Long id);

    Prestamo savePrestamo(Prestamo prestamo);

    void deleteById(Long id);

    List<Prestamo> findPrestamosByCuenta(Long numeroCuenta);
   
}
