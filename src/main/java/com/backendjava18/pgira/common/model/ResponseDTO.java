package com.backendjava18.pgira.common.model;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseDTO {
    private Object content;
    private boolean hasErrors;
    private List<String> errors;
    private String timestamp;
    private int status;
}
