package com.gy.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @InitBinder
    public void bind(WebDataBinder binder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 2020-10-10
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Date updateBirthday(Date date) {
        return date;
    }
}
