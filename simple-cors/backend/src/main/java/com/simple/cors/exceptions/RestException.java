package com.simple.cors.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.cors.dtos.ErrorDto;

@ControllerAdvice
public class RestException {
    
    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAppException(AppException ex) {
        return ResponseEntity
            .status(ex.getStatus())
            .body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException ex) {
        return ResponseEntity
            .internalServerError()
            .body(new ErrorDto("Unknown Error"));
    }

}
