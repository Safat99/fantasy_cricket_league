package com.sarker.fcl_main.common.generic.service;

import com.sarker.fcl_main.common.generic.entity.BaseEntity;
import com.sarker.fcl_main.common.generic.payload.request.IDto;
import com.sarker.fcl_main.common.generic.payload.request.SDto;
import com.sarker.fcl_main.common.generic.payload.response.PageData;
import com.sarker.fcl_main.common.generic.repository.AbstractRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractService<E extends BaseEntity, D extends IDto, S extends SDto> implements IService<E, D, S> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);
    private final AbstractRepository<E> repository;

    @Override
    public E create(D d) {

        E e = convertToEntity(d);

        return saveItem(e);
    }

    @Override
    public E update(D d, Long id) {

        E e = findById(id);

        validateClientData(d, e);

        return saveItem(updateEntity(d, e));
    }

    @Override
    public E saveItem(E entity) {
        try {

            return repository.save(entity);
        } catch (Exception e) {

            String entityName = entity.getClass().getSimpleName();

            LOGGER.error("Save failed for entity {}", entityName);
            LOGGER.error("Error message {}", e.getMessage());

            throw new RuntimeException("error happened");
        }
    }

    @Override
    public List<E> saveItemList(List<E> entityList) {
        return null;
    }

    @Override
    public E findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<E> findByIds(Collection<Long> ids) {
        try {

            return repository.findAllById(ids);
        } catch (Exception e) {

            LOGGER.error("Error message: {}", e.getMessage());
            throw new RuntimeException("List item Not found");
        }
    }

    @Override
    public <T> T getSingle(Long id) {
        return null;
    }

    @Override
    public PageData<E> getAll(Boolean isActive, Pageable pageable) {
        return null;
    }

    @Override
    public void updateActiveStatus(Long id, Boolean b) {
    }

    protected abstract <T> T convertToResponseDto(E e);

    protected abstract E convertToEntity(D d);

    protected abstract E updateEntity(D dto, E entity);

    protected void validateClientData(D d, E entity) {
    }
}
