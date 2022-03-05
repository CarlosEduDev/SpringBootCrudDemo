package com.simple.crud.demo.exception.handler;

import com.simple.crud.demo.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerPersonNotFoundException(Exception exception, WebRequest webRequest){
        var exceptionResponse = new ExceptionResponse(
                exception.getMessage(),
                webRequest.getDescription(false),
                new Date()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

}
