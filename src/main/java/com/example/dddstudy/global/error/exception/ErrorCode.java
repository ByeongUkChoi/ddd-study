package com.example.dddstudy.global.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    MISSING_REQUIRED_PARAMETERS(HttpStatus.BAD_REQUEST, 1001, "필수 값이 누락되었습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
