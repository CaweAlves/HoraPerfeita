package com.cawe.horaperfeita.domain.interfaces;

import java.util.List;

public interface IBaseService<BaseEntity> {
    public BaseEntity findById(Long id);

    public List<BaseEntity> findAll();

    public BaseEntity create(BaseEntity entity);

    public BaseEntity update(BaseEntity entity);

    public void remove(Long id);
}
