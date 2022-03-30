package com.gy.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String pwd;
    private String cpwd;
    private String email;
    private String phone;
}
