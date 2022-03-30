package com.gy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {
    /**
     * name来自于url的路径参数；
     * model是用来容纳数据，最终传递给View
     * @param name
     * @param model
     * @return
     */
    // http://localhost:8080/sayHello
    @RequestMapping(method = RequestMethod.GET,
            value="sayHello")
    public String sayHello(String name, Model model) {
        // 往model添加名为name的数据
        model.addAttribute("name", name);
        return "demo";
    }
}
