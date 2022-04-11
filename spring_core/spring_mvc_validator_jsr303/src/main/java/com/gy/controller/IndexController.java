package com.gy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        // 判断下session中有没有用户？
        // session中没有用户的话，重定向到login页面
        // return "redirect:/login";
        return "forward:/login";
    }
}
