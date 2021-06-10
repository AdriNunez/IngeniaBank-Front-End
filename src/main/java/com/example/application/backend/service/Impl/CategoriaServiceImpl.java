package com.example.application.backend.service.Impl;

import com.example.application.backend.model.Categoria;
import com.example.application.backend.repository.CategoriaReporitory;
import com.example.application.backend.service.CategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
    private final CategoriaReporitory categoriaReporitory;

    public CategoriaServiceImpl(CategoriaReporitory categoriaReporitory) {
        this.categoriaReporitory = categoriaReporitory;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaReporitory.findAll();
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaReporitory.getOne(id);
    }

    @Override
    public Categoria saveCuenta(Categoria categoria) {

        if(ObjectUtils.isEmpty(categoria))
            return null;

        return categoriaReporitory.save(categoria);
    }

    @Override
    public void deleteById(Long id) {
        categoriaReporitory.deleteById(id);
    }
}
