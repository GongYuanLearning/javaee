package com.gy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    // http://localhost:8080/sayHello
    @RequestMapping(method = RequestMethod.GET,
            value="sayHello")
    @ResponseBody
    public String sayHello(String name) {
        return String.format("你好, %s!", name);
    }

    @RequestMapping(method = RequestMethod.GET,
            value="demo")
    public String demo(String name, Model model) {
        model.addAttribute("name", name);
        return "demo";
    }
}
