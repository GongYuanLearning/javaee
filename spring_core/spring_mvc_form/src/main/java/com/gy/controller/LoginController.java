package com.gy.controller;

import com.gy.dto.LoginDto;
import com.gy.model.User;
import com.gy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",
            method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value="/login",
            method = RequestMethod.POST)
    public ResponseEntity<Object> handle(@RequestBody LoginDto loginDto) {
        try {
            User user = userService.login(loginDto);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch(IllegalArgumentException e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("error", e.getMessage());
            return new ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
