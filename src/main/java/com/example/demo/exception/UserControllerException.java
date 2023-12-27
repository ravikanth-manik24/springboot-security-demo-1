package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<UserErrorResponse> accessDeniedException(AccessDeniedException ex) {
        UserErrorResponse errorResponse = new UserErrorResponse();
        errorResponse.setMessage("Access is denied for this API with current user, " +
                "please login with correct user role");
        errorResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
