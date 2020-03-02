package com.student.community.exception;

import com.student.community.enums.StatusCode;

public class CustomizeException extends RuntimeException {
    private String message;
    private String type;

    public CustomizeException(StatusCode statusCode) {
        this.message = statusCode.getDesc();
        this.type=statusCode.getType();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
