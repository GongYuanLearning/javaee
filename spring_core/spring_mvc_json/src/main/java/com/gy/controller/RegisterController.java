package com.gy.controller;

import com.gy.dao.UserDao;
import com.gy.dto.RegisterDto;
import com.gy.model.User;
import com.gy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
//@ResponseBody
//@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    /**
     * RequestBody是用来接收请求体转换而来的参数.
     *
     * @param registerDto
     * @return
     */
    @RequestMapping(value = "/handle",
            method = RequestMethod.POST)
    //@ResponseBody
    @ResponseStatus(HttpStatus.CREATED) // 指明响应的状态码，新建数据使用201
    public User handleJson(@RequestBody RegisterDto registerDto) {
        User user = userService.add(registerDto);
        return user;
    }
}
