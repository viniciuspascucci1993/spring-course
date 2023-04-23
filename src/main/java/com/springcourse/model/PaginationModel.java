package com.springcourse.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationModel<T> implements Serializable {

    /*
     * Serial Version UUID
     * */
    private static final long serialVersionUID = 1L;

    private int totalElements;
    private int pageSize;
    private int totalPages;
    private List<T> elements;
}
