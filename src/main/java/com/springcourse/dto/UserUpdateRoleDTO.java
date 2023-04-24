package com.springcourse.dto;

import com.springcourse.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRoleDTO {

    private Role role;
}
