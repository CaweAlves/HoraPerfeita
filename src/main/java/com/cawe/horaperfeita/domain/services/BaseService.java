package com.cawe.horaperfeita.domain.services;

import com.cawe.horaperfeita.domain.interfaces.services.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseService<BaseEntity> implements IBaseService {

    public JpaRepository<BaseEntity, Long> repository;

    public BaseService() {
        this.repository = null;
    }

    @Autowired
    public void setRepository(JpaRepository<BaseEntity, Long> repository) {
        this.repository = repository;
    }

    public BaseEntity buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<BaseEntity> buscarTodos() {
        return repository.findAll();
    }

    public BaseEntity criar(BaseEntity entity) {
        return repository.save(entity);
    }

    public BaseEntity atualizar(BaseEntity entity) {
        return repository.save(entity);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

}
