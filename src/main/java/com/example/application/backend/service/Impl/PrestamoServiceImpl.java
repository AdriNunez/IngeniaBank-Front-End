package com.example.application.backend.service.Impl;

import com.example.application.backend.dao.PrestamoDAO;
import com.example.application.backend.dao.impl.PrestamoDAOImpl;
import com.example.application.backend.model.Prestamo;
import com.example.application.backend.repository.PrestamoRepository;
import com.example.application.backend.service.PrestamoService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final PrestamoDAO prestamoDAO;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, PrestamoDAO prestamoDAO) {
        this.prestamoRepository = prestamoRepository;
        this.prestamoDAO = prestamoDAO;
    }

    @Override
    public List<Prestamo> findAll() {
        return  prestamoRepository.findAll();
    }

    @Override
    public Prestamo findPrestamoById(Long id) {
        return prestamoDAO.findPrestamoById(id);
    }


    @Override
    public Prestamo savePrestamo(Prestamo prestamo) {

        if(ObjectUtils.isEmpty(prestamo))
            return null;

        return prestamoRepository.save(prestamo);
    }



    @Override
    public void deleteById(Long id) {
        prestamoRepository.deleteById(id);
    }

    @Override
    public List<Prestamo> findPrestamosByCuenta(Long numeroCuenta) {
        return null;
    }

    @Override
    public Optional<Prestamo> findById(Long id) {

        return prestamoRepository.findById(id);
    }
}
