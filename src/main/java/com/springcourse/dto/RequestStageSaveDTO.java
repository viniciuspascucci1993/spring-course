package com.springcourse.dto;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestStageSaveDTO {

    @NotBlank(message = "Description is Required!")
    private String description;

    @NotNull(message = "State is Required!")
    private RequestState state;

    @NotNull(message = "Request is Required!")
    private Request request;

    @NotNull(message = "Owner of this request is Required!")
    private User owner;

    public RequestStage transformToRequestStage() {
        RequestStage requestStage = RequestStage.builder()
                .id(null)
                .description(this.description)
                .realizationDate(null)
                .state(this.state)
                .request(this.request)
                .owner(this.owner)
                .build();

        return requestStage;
    }
}
