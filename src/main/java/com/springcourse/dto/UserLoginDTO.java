package com.springcourse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserLoginDTO {

    @Email(message = "Invalid E-mail Address, Please provide a valid E-mail!")
    private String email;

    @NotBlank(message = "Password is Required!")
    private String password;
}
