package com.example.dddstudy.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNKNOWN_ERROR(1000, "UNKNOWN ERROR"),
    MISSING_REQUIRED_PARAMETERS(1001, "필수 값이 누락되었습니다.");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
