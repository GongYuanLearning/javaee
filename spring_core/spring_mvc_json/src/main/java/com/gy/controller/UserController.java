package com.gy.controller;

import com.gy.dao.UserDao;
import com.gy.model.User;
import com.gy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired private UserService userService;

    @RequestMapping("/getMe/{username}")
    public String getMe(@PathVariable("username") String username,
                        Model model) {
        model.addAttribute("username", username);
        return "user";
    }

    @RequestMapping("/getMeById/{id}")
    @ResponseBody
    public User getMeById(@PathVariable("id") long id,
                          Model model) {
        return userService.getById(id);
    }
}
