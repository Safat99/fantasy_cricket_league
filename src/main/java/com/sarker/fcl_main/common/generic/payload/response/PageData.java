package com.sarker.fcl_main.common.generic.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

}
