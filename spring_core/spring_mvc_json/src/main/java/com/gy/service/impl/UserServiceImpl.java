package com.gy.service.impl;

import com.gy.dao.UserDao;
import com.gy.dto.LoginDto;
import com.gy.dto.RegisterDto;
import com.gy.model.User;
import com.gy.service.UserService;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Override
    public User add(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPhone(registerDto.getPhone());
        user.setEmail(registerDto.getEmail());
        // encryptPassword 加密密码
        user.setPwdHash(passwordEncryptor.encryptPassword(registerDto.getPwd()));
        userDao.add(user);
        return user;
        //return userDao.getById(user.getId());
    }


    @Override
    public User login(LoginDto loginDto) {
        User user = this.userDao.findByUsername(loginDto.getUsername());
        if(null == user) {
            throw new IllegalArgumentException(
                    String.format("用户：%s不存在！", loginDto.getUsername()));
        }
        if(!this.passwordEncryptor.checkPassword(loginDto.getPwd(), user.getPwdHash())) {
            throw new IllegalArgumentException(
                    String.format("用户：%s密码错误！", loginDto.getUsername()));
        }
        return user;
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }
}
