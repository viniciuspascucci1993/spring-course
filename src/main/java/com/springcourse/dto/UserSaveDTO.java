package com.springcourse.dto;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.enums.Role;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDTO {

    @NotBlank
    private String name;
    @Email(message = "Invalid E-mail Address!")
    private String email;
    @Size(min = 7, max = 99, message = "Password Must be between 7 and 99")
    private String password;

    @NotNull(message = "Registration is Required!")
    private Integer registration;

    @NotNull(message = "Code is Required!")
    private Integer code;

    @NotNull(message = "Please provide at least a ROLE!")
    private Role role;


    private List<Request> requests = new ArrayList<Request>();
    private List<RequestStage> stages = new ArrayList<RequestStage>();

    public User transformToUser() {
        User user = User.builder()
                .id(null)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .registration(this.registration)
                .code(this.code)
                .role(this.role)
                .requests(this.requests)
                .stages(this.stages)
                .build();

        return user;
    }
}
