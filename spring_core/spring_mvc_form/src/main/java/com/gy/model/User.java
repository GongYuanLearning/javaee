package com.gy.model;

import lombok.Data;

@Data
public class User {
    private long id;
    private String username;
    private String pwdHash;
    private String email;
    private String phone;
}
