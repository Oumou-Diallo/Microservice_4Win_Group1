package com.micro.userservice.dto;


import com.micro.userservice.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String email;
    private String username;
    private String password;
    private UserRole userRole;

    public UserResponse() {

    }
}
