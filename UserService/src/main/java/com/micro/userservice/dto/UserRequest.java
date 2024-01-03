package com.micro.userservice.dto;

import com.micro.userservice.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRequest {
    private String email;
    private String username;
    private String password;
    private UserRole userRole;

    public UserRequest() {

    }
}
