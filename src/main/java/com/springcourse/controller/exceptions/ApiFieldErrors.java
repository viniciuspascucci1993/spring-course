package com.springcourse.controller.exceptions;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApiFieldErrors extends ApiError {

    /*
     * Serial Version UUID
     * */
    private static final long serialVersionUID = 1L;

    private List<String> errors;
    public ApiFieldErrors(int code, String message, Date date, List<String> errors) {
        super(code, message, date);
        this.errors = errors;
    }
}
