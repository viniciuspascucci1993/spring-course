package com.springcourse.dto;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSaveDTO {

    @NotBlank(message = "Subject is Required!")
    private String subject;

    @NotBlank(message = "Description is Required!")
    private String description;

    @NotNull(message = "Request's code is Required!")
    private Integer code;

    @NotNull(message = "Owner of this request is Required!")
    private User owner;
    private List<RequestStage> stages = new ArrayList<>();

    public Request transformToRequest() {
        Request request = Request.builder()
                .id(null)
                .subject(this.subject)
                .description(this.description)
                .creationDate(null)
                .state(null)
                .code(this.code)
                .owner(this.owner)
                .build();

        return request;
    }
}
