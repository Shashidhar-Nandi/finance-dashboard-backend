package com.finance.dashboard.dto;

import com.finance.dashboard.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String name;
    private String email;
    private String password;

    private Role role;   // ✅ MUST be Role
}