package com.gy.controller;

import com.gy.dto.RegisterDto;
import com.gy.model.User;
import com.gy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    /*    *//**
             * 往model加属性，key默认是该方法的返回类型；
             * value是该方法的返回值；
             * 该方法会在请求处理方法之前调用。
             * key: stringList
             *
             * @return
             *//*
                * @ModelAttribute("hobbies")
                * public List<String> hobbies() {
                * List<String> hobbies = new ArrayList<>();
                * hobbies.add("篮球");
                * hobbies.add("羽毛球");
                * hobbies.add("游泳");
                * return hobbies;
                * }
                */

    /**
     * RequestMapping不传递method属性值，
     * 默认就是对GET,POST进行响应。
     * 
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(Model model) {
        return "register";
    }

    @RequestMapping(value = "/show1", method = RequestMethod.GET)
    public ModelAndView show(ModelAndView modelAndView) {
        // model.addAttribute("user", new User());
        User user = new User();
        user.setUsername("张三");
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    /**
     * 返回的User将作为Model的属性，并且属性名user。
     * 视图名：类的RequestMapping注解中的路径+'/'+方法名。
     * /register/register
     *
     * @return
     */
    @RequestMapping(value = "/register")
    /*
     * public User register(HttpServletRequest request,
     * HttpServletResponse response,
     * HttpSession session) {
     */
    public User register(@RequestParam(value = "username", required = false, defaultValue = "lzj") String username,
            @RequestHeader String pwd) {
        User user = new User();
        user.setUsername("张三");
        return user;
    }

    /**
     * 对register表单进行响应。
     * 
     * @param username
     * @param pwd
     * @param cpwd
     * @param email
     * @param phone
     * @return
     */
    /*
     * @RequestMapping(value = "/handle",
     * method = RequestMethod.POST)
     * public String handle(RegisterDto registerDto) {
     * return "login";
     * }
     */
    /*
     * @RequestMapping(value = "/handle",
     * method = RequestMethod.POST)
     * public String handle(@RequestParam String username,
     * String pwd,
     * String cpwd,
     * String email,
     * String phone) {
     * return "login";
     * }
     */
    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    public String handle(@ModelAttribute("registerDto") RegisterDto registerDto,
            Model model) {
        // model.addAttribute("registerDto", registerDto);
        User user = userService.add(registerDto);
        model.addAttribute("username", user.getUsername());
        return "login";
    }
}
