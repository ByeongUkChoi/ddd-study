package com.example.dddstudy.global.error.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException {
    @Getter
    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
