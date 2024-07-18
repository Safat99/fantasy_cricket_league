package com.sarker.fcl_main.common.generic.repository;

import com.sarker.fcl_main.common.generic.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;

@NoRepositoryBean
public interface AbstractRepository<E extends BaseEntity> extends JpaRepository<E, Long> {

    Page<E> findAllByIsActive(Boolean isActive, Pageable pageable);

    List<E> findAllByIdIn(Collection<Long> ids);
}
