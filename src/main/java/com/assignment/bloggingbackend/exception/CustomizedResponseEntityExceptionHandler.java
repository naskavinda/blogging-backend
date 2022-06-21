package com.assignment.bloggingbackend.exception;

import com.assignment.bloggingbackend.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import static com.assignment.bloggingbackend.util.ResponseDetails.E1002;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<Response<Void>> constraintViolationException(ConstraintViolationException exception, WebRequest request) {
        String description = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(E1002.getCode(), description));
    }


    @ExceptionHandler(BloggingException.class)
    private ResponseEntity<Response<Void>> bloggingException(BloggingException exception, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(exception.getCode(), exception.getDescription()));
    }

}
