package com.sarker.fcl_main.common.generic.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {

    private String message;
    private Long id;
    private Integer status;

    public MessageResponse(String message, Long id, Integer status) {
        this.message = message;
        this.id = id;
        this.status = status;
    }

    public MessageResponse(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    public MessageResponse(String message) {
        this.message = message;
    }
}
