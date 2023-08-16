package com.stringconcat.integration.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseStatusResponse {

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAIL = "fail";

    private String status;
}
