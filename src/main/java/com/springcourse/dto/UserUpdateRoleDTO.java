package com.springcourse.dto;

import com.springcourse.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRoleDTO {

    @NotNull(message = "Please provide at least a ROLE!")
    private Role role;
}
