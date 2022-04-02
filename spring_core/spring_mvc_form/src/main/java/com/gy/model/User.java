package com.gy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties({"pwdHash"})
public class User {
    private long id;
    private String username;
    private String pwdHash;
    private String email;
    private String phone;
}
