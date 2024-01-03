package com.micro.userservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum UserRole {
    ADMIN,USER,GUEST;

    //private final String value;

   /* UserRole(String value) {
        this.value = value;
    }

    public static UserRole fromValue(String value) {
        for (UserRole role : UserRole.values()) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown UserRole value: " + value);
    }*/
}
