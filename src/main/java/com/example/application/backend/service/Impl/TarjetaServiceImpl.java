package com.example.application.backend.service.Impl;
import com.example.application.backend.dao.TarjetaDAO;

import com.example.application.backend.model.Tarjeta;
import com.example.application.backend.repository.TarjetaRepository;
import com.example.application.backend.service.TarjetaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    public TarjetaDAO tarjetaDAO;
    private TarjetaRepository tarjetaRepository;

    public TarjetaServiceImpl(TarjetaDAO tarjetaDAO,TarjetaRepository tarjetaRepository) {
        this.tarjetaDAO = tarjetaDAO;
        this.tarjetaRepository = tarjetaRepository;
    }

    @Override
    public Tarjeta createTarjeta(Tarjeta tarjeta){ return this.tarjetaDAO.createTarjeta(tarjeta);}

    @Override
    public Tarjeta updateTarjeta(Tarjeta tarjeta){return this.tarjetaDAO.updateTarjeta(tarjeta);}

    @Override
    public void deleteTarjeta(Long numeroTarjeta){this.tarjetaDAO.deleteTarjeta(numeroTarjeta);};

    @Override
    public List<Tarjeta> findTarjetas(){return this.tarjetaDAO.findTarjetas();}

    @Override
    public Optional<Tarjeta> findOneTarjeta(Long numeroTarjeta) {

            return this.tarjetaDAO.findOneTarjeta(numeroTarjeta);

    }

    @Override
    public List<Tarjeta> findTarjetasByCuenta(Long numeroCuenta){
        return this.tarjetaDAO.findTarjetasByCuenta(numeroCuenta);
    }

    @Override
    public Double getSaldoTotalCuenta(Long id) {
        return this.tarjetaRepository.getSaldoTotalCuenta(id);
    }

    @Override
    public Tarjeta findById(Long id) {
        return tarjetaRepository.getOne(id);
    }
}
