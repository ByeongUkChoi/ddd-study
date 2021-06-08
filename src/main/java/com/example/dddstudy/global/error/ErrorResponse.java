package com.example.dddstudy.global.error;

import com.example.dddstudy.global.error.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        code = errorCode.getCode();
        message = errorCode.getMessage();
    }
}
