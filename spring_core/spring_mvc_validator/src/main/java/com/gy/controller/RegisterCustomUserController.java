package com.gy.controller;

import com.gy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
public class RegisterCustomUserController {
    // http://localhost:8080/demo/custom/user?u=zhangshan-123-123@test.com-12345
    // @RequestMapping(value = "/custom/user", method = RequestMethod.GET)
    @GetMapping("/custom/user")
    //    @PostMapping
    //    @PutMapping
    //    @PatchMapping
    //    @DeleteMapping
    @ResponseBody
    public User add(@RequestParam("u") User user, @RequestParam("b") BigDecimal balance) {
        return user;
    }
}
