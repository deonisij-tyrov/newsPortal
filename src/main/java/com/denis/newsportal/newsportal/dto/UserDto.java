package com.denis.newsportal.newsportal.dto;

import com.denis.newsportal.newsportal.enumeration.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String login;
    private String password;
    private String role;
}
