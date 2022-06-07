package com.example.mycli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AccountNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AccountNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    String accountNotFound(AccountNotFound ex) {
        return ex.getMessage();
    }
}
