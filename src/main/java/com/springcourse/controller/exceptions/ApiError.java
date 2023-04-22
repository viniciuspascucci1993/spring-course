package com.springcourse.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError implements Serializable {

    /*
     * Serial Version UUID
     * */
    private static final long serialVersionUID = 1L;

    private int code;
    private String datailMessage;
    private Date date;
}
