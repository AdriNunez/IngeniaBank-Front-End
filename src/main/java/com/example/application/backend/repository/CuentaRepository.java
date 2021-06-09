package com.example.application.backend.repository;


import com.example.application.backend.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    @Query("SELECT SUM(importe) from Movimiento where cuenta.id=?1")
    Double getSaldoTotalCuenta(Long numerocuenta);


}
