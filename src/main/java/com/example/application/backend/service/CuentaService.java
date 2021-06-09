package com.example.application.backend.service;


import com.example.application.backend.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaService {
    List<Cuenta> findAll();

   Cuenta findById(Long id);

    Cuenta saveCuenta(Cuenta cuenta);

   void deleteById(Long id);
    Double getSaldoTotalCuenta(Long id);




}
