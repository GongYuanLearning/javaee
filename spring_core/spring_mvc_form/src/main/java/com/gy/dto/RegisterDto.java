package com.gy.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterDto {
    private String username;
    private Date birthday;
    private Double balance;
    private String pwd;
    private String cpwd;
    private String email;
    private String phone;
    private String[] hobbies; // 爱好
    private String role; // 角色
}
