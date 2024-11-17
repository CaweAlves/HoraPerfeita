package com.cawe.horaperfeita.domain.services;

import com.cawe.horaperfeita.domain.interfaces.services.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseService<BaseEntity> {

    public JpaRepository<BaseEntity, Long> repository;

    public BaseService() {
        this.repository = null;
    }

    @Autowired
    public void setRepository(JpaRepository<BaseEntity, Long> repository) {
        this.repository = repository;
    }

    public BaseEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<BaseEntity> findAll() {
        return repository.findAll();
    }

    public BaseEntity create(BaseEntity entity) {
        return repository.save(entity);
    }

    public BaseEntity update(BaseEntity entity) {
        return repository.save(entity);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

}
