package com.gy.service;

import com.gy.dto.LoginDto;
import com.gy.dto.RegisterDto;
import com.gy.model.User;

public interface UserService {
    User add(RegisterDto registerDto);
    User getById(long id);
    User login(LoginDto loginDto);
}
