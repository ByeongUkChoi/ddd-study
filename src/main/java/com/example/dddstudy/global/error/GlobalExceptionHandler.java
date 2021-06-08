package com.example.dddstudy.global.error;

import com.example.dddstudy.global.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleException", e);
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.UNKNOWN_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
