package com.sarker.fcl_main.common.generic.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PageData<T> {

    private Collection<T> model;
    private int totalPages;
    private int currentPage;
    long totalElements;

    public PageData<T> convertToPageData(Page<T> pagedData, Pageable pageable) {

        Collection<T> models = pagedData.getContent()
                .stream()
                .toList();

        return PageData.<T>builder()
                .model(models)
                .totalPages(pagedData.getTotalPages())
                .totalElements(pagedData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .build();
    }

}
