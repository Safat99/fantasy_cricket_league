package com.sarker.fcl_main.common.generic.controller;

import com.sarker.fcl_main.common.generic.entity.AbstractEntity;
import com.sarker.fcl_main.common.generic.payload.request.IDto;
import com.sarker.fcl_main.common.generic.payload.request.SDto;
import com.sarker.fcl_main.common.generic.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface IController<E extends AbstractEntity, D extends IDto, S extends SDto> {

    ResponseEntity<MessageResponse> create(D d);

    ResponseEntity<MessageResponse> update(D d, Long id);

    ResponseEntity<MessageResponse> updateActiveStatus(Long id, Boolean isActive);

     <T> T getSingle(Long id);

}