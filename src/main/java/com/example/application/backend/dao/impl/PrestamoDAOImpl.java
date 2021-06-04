package com.example.application.backend.dao.impl;

import com.example.application.backend.dao.PrestamoDAO;
import com.example.application.backend.model.Movimiento;
import com.example.application.backend.model.Prestamo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;
@Repository
public class PrestamoDAOImpl implements PrestamoDAO {


    @PersistenceContext
    private EntityManager manager;

    public Prestamo findPrestamoById(Long id){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Prestamo> criteria = builder.createQuery(Prestamo.class);
        Root<Prestamo> root =     criteria.from(Prestamo.class);

        criteria.select(root);
        criteria.where(builder.equal(root.get("id"), id));
        return manager.createQuery(criteria).getSingleResult();
    }

}
