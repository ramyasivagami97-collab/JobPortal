package com.example.JobPortal;

import com.example.JobPortal.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(userNotFoundExceptions.class)
    public ResponseEntity<String> handleUserNotFound(userNotFoundExceptions ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(applicationNotFoundExceptions.class)
    public ResponseEntity<String> handleApplicationNotFound(applicationNotFoundExceptions ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(jobNotFoundExceptions.class)
    public ResponseEntity<String> handleJobNotFound(jobNotFoundExceptions ex){
        return  new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(companyNotFoundExceptions.class)
    public ResponseEntity<String> handleCompanyNotFound(companyNotFoundExceptions ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(skillNotFoundExceptions.class)
    public ResponseEntity<String> handleSkillNotFound(skillNotFoundExceptions ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
