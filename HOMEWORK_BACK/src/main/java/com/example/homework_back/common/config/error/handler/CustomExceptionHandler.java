package com.example.homework_back.common.config.error.handler;

import com.example.homework_back.common.config.error.dto.ErrorDto;
import com.example.homework_back.common.config.error.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorDto> handleCustom400Exception(CustomException ex) {
        return ErrorDto.toResponseEntity(ex);
    }
}
