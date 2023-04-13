package com.springcourse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Request {

    private Long id;
    private String subject;
    private String description;
    private Date creationDate;
    private Integer code;
    private List<RequestStage> stages = new ArrayList<>();
}
