package com.stringconcat.integration.controller.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuccessResponse extends BaseStatusResponse {

    private Object data;

    public SuccessResponse(Object data) {
        super(STATUS_SUCCESS);
        this.data = data;
    }
}
