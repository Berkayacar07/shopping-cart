package com.example.shoppingcart.component.advice;

import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.example.shoppingcart.exception.CustomerNotFoundException;
import com.example.shoppingcart.response.CustomExceptionResponse;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import java.nio.file.*;
import java.util.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<CustomExceptionResponse> handleBadCredentialsException(BadCredentialsException ex) {
        CustomExceptionResponse errorResponse = new CustomExceptionResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(ServletException.class)
    public final ResponseEntity<CustomExceptionResponse> handleServletException(ServletException ex) {
        CustomExceptionResponse errorResponse = new CustomExceptionResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<CustomExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        CustomExceptionResponse errorResponse = new CustomExceptionResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(PSQLException.class)
    public final ResponseEntity<CustomExceptionResponse> handlePSQLException(PSQLException ex) {
        CustomExceptionResponse errorResponse = new CustomExceptionResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<CustomExceptionResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        CustomExceptionResponse errorResponse = new CustomExceptionResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(DisabledException.class)
    public final ResponseEntity<CustomExceptionResponse> handleDisabledException(DisabledException ex) {
        CustomExceptionResponse errorResponse = new CustomExceptionResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<CustomExceptionResponse> handleDisabledException() {
        CustomExceptionResponse errorResponse = new CustomExceptionResponse();
        errorResponse.setMessage("You do not have permission. You are not authorized to perform this action!");
        errorResponse.setTimestamp(new Date());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<CustomExceptionResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        CustomExceptionResponse errorResponse = new CustomExceptionResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
