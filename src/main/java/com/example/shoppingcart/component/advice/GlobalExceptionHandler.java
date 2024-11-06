package com.example.shoppingcart.component.advice;

import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.example.shoppingcart.response.CustomExceptionResponse;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import java.nio.file.*;
import java.util.Date;

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
    
    @ExceptionHandler(FileAlreadyExistsException.class)
    public final ResponseEntity<CustomExceptionResponse> handleIllegalArgumentException() {
        CustomExceptionResponse errorResponse = new CustomExceptionResponse();
        errorResponse.setMessage("File already exists");
        errorResponse.setTimestamp(new Date());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
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
}
