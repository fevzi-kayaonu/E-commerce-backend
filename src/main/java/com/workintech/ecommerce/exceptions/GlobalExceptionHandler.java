package com.workintech.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorResponse> handleException(ErrorException errorException){
        ErrorResponse errorResponse =
                new ErrorResponse(errorException.getStatus().value(),
                        errorException.getMessage(),
                        LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, errorException.getStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetailsWithValidation> handleException(MethodArgumentNotValidException ex,
                                                                      WebRequest request) {
        //key, value pair
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        errorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorDetailsWithValidation validationErrors = new ErrorDetailsWithValidation(
                LocalDateTime.now(), request.getDescription(false),
                HttpStatus.BAD_REQUEST.value(), errors);

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception){
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage(),
                        LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}


