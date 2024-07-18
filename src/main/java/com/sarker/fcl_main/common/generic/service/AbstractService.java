package com.sarker.fcl_main.common.generic.service;

import com.sarker.fcl_main.common.generic.entity.BaseEntity;
import com.sarker.fcl_main.common.generic.payload.request.IDto;
import com.sarker.fcl_main.common.generic.payload.request.SDto;
import com.sarker.fcl_main.common.generic.payload.response.PageData;
import com.sarker.fcl_main.common.generic.repository.AbstractRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

        try {
            if (CollectionUtils.isEmpty(entityList)) {
                return entityList;
            }
            return repository.saveAll(entityList);
        } catch (Exception e) {
            String entityName = entityList.getFirst().getClass().getSimpleName();
            LOGGER.error("Save failed for entity {}", entityName);
            LOGGER.error("Error message: {}", e);
            throw new RuntimeException(entityName + " " + "not found");
        }
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
        return convertToResponseDto(findById(id));
    }

    @Override
    public PageData<?> getAll(Boolean isActive, Pageable pageable) {
        Page<E> pagedData = repository.findAllByIsActive(isActive, pageable);
        return convertToPageData(pagedData, pageable);
    }


    @Override
    public void updateActiveStatus(Long id, Boolean isActive) {
        E e = findById(id);

        if (Objects.equals(e.getIsActive(), isActive)) {
            throw new RuntimeException("only toggle value is accepted");
        }

        e.setIsActive(isActive);
        saveItem(e);
    }

    protected abstract <T> T convertToResponseDto(E e);

    protected abstract E convertToEntity(D d);

    protected abstract E updateEntity(D dto, E entity);

    protected void validateClientData(D d, E entity) {
    }

    protected PageData<?> convertToPageData(Page<E> pagedData, Pageable pageable) {

        Collection<Object> models = pagedData.getContent()
                .stream()
                .map(this::convertToResponseDto)
                .toList();

        return PageData.builder()
                .model(models)
                .totalPages(pagedData.getTotalPages())
                .totalElements(pagedData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .build();
    }
}
