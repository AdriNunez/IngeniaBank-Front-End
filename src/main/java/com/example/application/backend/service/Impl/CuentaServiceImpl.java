package com.example.application.backend.service.Impl;


import com.example.application.backend.dao.CuentaDao;

import com.example.application.backend.repository.CuentaRepository;
import com.example.application.backend.service.CuentaService;
import com.example.application.backend.model.Cuenta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {
    private final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    private final CuentaRepository cuentaRepository;
    private final CuentaDao cuentaDao;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, CuentaDao cuentaDao) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaDao = cuentaDao;
    }

    @Override

    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta findById(Long id) {
        return  cuentaRepository.getOne(id);
    }


    @Override
    public Cuenta saveCuenta(Cuenta cuenta) {

        if(ObjectUtils.isEmpty(cuenta))
            return null;

        return cuentaRepository.save(cuenta);
    }
    @Override
    public void deleteById(Long id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public Double getSaldoTotalCuenta(Long id) {
        return cuentaRepository.getSaldoTotalCuenta(id);
    }


}
