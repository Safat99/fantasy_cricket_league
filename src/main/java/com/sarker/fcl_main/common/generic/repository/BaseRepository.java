package com.sarker.fcl_main.common.generic.repository;

import com.sarker.fcl_main.common.generic.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends AbstractEntity, ID> extends JpaRepository<T, ID> {
}
