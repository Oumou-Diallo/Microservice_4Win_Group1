package com.micro.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "commentaire")
@Builder
@Data
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String email;
    private String username;
    private String password;
    private UserRole userRole;
}
