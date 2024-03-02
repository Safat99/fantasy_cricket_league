package com.sarker.fcl_main.common.generic.repository;

import com.sarker.fcl_main.common.generic.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {

    Page<E> findAllByIsActive(Boolean isActive, Pageable pageable);
}
