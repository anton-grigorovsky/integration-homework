package com.stringconcat.integration.controller.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FailResponse extends BaseStatusResponse {

    private String errorMessage;

    public FailResponse(String errorMessage) {
        setStatus(STATUS_FAIL);
        this.errorMessage = errorMessage;
    }
}
