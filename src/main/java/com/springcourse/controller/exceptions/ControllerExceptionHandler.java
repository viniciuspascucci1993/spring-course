package com.springcourse.controller.exceptions;

import com.springcourse.exceptions.NotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String DETAIL_MESSAGE = "Invalid Fields";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handlerNotFound(NotFoundException ex) {
        ApiError apiError = new ApiError(404, ex.getMessage(), new Date());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                              HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            errors.add(objectError.getDefaultMessage());
        });

        ApiFieldErrors error = new ApiFieldErrors(HttpStatus.BAD_REQUEST.value(), DETAIL_MESSAGE, new Date(), errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }
}
