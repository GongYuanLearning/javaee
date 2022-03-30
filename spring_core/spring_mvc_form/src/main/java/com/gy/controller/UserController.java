package com.gy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/getMe/{username}")
    public String getMe(@PathVariable("username") String username,
                        Model model) {
        model.addAttribute("username", username);
        return "user";
    }
    @RequestMapping("/getMeById/{id}")
    public String getMeById(@PathVariable("id") long id,
                        Model model) {
        model.addAttribute("id", id);
        return "user";
    }
}
