package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerException {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<UserErrorResponse> userNotFoundException(UserNotFoundException ex) {
        UserErrorResponse errorResponse = new UserErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<UserErrorResponse> badRequestException(Exception ex) {
        UserErrorResponse errorResponse = new UserErrorResponse();
        errorResponse.setMessage("Bad Request, Please use correct type in the API");
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
