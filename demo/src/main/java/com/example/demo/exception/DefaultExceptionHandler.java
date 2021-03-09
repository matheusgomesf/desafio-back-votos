package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class DefaultExceptionHandler{

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerConstraintViolationException(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> fieldErrors = exception.getConstraintViolations();

        String filds = fieldErrors.stream().map(ConstraintViolation::getPropertyPath).map(Path::toString).collect(Collectors.joining(", "));
        String fildsMessage = fieldErrors.stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.joining(", "));

        ValidationExceptionDetails validationExceptionDetails = ValidationExceptionDetails.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field validation erro")
                .detail(exception.getMessage())
                .developerMessage(exception.getClass().getSimpleName())
                .filds(filds)
                .fildMessage(fildsMessage)
                .build();
        return new ResponseEntity<>(validationExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();

        String filds = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fildsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        ValidationExceptionDetails validationExceptionDetails = ValidationExceptionDetails.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field validation erro")
                .detail(exception.getLocalizedMessage())
                .developerMessage(exception.getClass().getSimpleName())
                .filds(filds)
                .fildMessage(fildsMessage)
                .build();
        return new ResponseEntity<>(validationExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDatails> handlerEntityNotFoundException(EntityNotFoundException exception) {
        ExceptionDatails validationExceptionDetails = ExceptionDatails.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Object not found")
                .detail(exception.getLocalizedMessage())
                .developerMessage(exception.getClass().getSimpleName())
                .build();
        return new ResponseEntity<>(validationExceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
