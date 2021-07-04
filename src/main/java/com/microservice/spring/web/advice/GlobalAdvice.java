package com.microservice.spring.web.advice;

import com.microservice.spring.web.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalAdvice {

    @ResponseBody // advice is rendered str8 into response body
    @ExceptionHandler(EmployeeNotFoundException.class) // configures the advice to only response IF EmployeeNotFoundException is thrown
    @ResponseStatus(HttpStatus.NOT_FOUND) // issue 404 for response
    String employeeNotFoundExceptionHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }

}
