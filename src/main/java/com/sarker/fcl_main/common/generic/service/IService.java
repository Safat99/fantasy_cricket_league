package com.sarker.fcl_main.common.generic.service;

import com.sarker.fcl_main.common.generic.entity.AbstractEntity;
import com.sarker.fcl_main.common.generic.payload.request.IDto;
import com.sarker.fcl_main.common.generic.payload.request.SDto;

import java.util.Collection;
import java.util.List;

public interface IService<E extends AbstractEntity, D extends IDto, S extends SDto> {

    E create(D d);

    E update(D d, Long id);

    E saveItem(E entity);

    List<E> saveItemList(List<E> entityList);

    E findById(Long id);

    List<E> findByIds(Collection<Long> ids);

    <T> T getSingle(Long id);

    void updateActiveStatus(Long id, Boolean b);

}
