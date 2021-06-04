package com.example.application.backend.repository;

import com.example.application.backend.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo,Long> {

}
