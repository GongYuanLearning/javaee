package com.gy.controller;

import com.gy.dto.RegisterDto;
import com.gy.editor.DoubleEditor;
import com.gy.model.User;
import com.gy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    /**
     * 往model加属性，key默认是该方法的返回类型；
     * value是该方法的返回值；
     * 该方法会在请求处理方法之前调用。
     * key: stringList
     *
     * @return
     */
    @ModelAttribute("hobbies")
    public Map<String, String> hobbies() {
        Map<String, String> hobbies = new HashMap<>();
        hobbies.put("1", "篮球");
        hobbies.put("2", "羽毛球");
        hobbies.put("3", "游泳");
        return hobbies;
    }

    @ModelAttribute("roles")
    public Map<String, String> roles() {
        Map<String, String> roles = new HashMap<>();
        roles.put("1", "消费者");
        roles.put("2", "管理员");
        return roles;
    }

    /*public List<String> hobbies() {
        List<String> hobbies = new ArrayList<>();
        hobbies.add("篮球");
        hobbies.add("羽毛球");
        hobbies.add("游泳");
        return hobbies;
    }*/

    /**
     * RequestMapping不传递method属性值，
     * 默认就是对GET,POST进行响应。
     * @return
     */
    @RequestMapping(value = "/show",
            method = RequestMethod.GET)
    public String show(Model model) {
        model.addAttribute("registerDto",
                new RegisterDto());
        return "register";
    }

    /**
     * BindingResult必须跟在@Validated或者@Valid注解后面，否则，400.
     *
     * @param registerDto
     * @param result
     * @param model
     * @return
     */
    @PostMapping(value = "/handle")
    public String handle(@Validated @ModelAttribute RegisterDto registerDto,
                         BindingResult result,
                         Model model) {
        if(result.hasErrors()) {
            return "register";
        }
        // model.addAttribute("registerDto", registerDto);
        User user = userService.add(registerDto);
        model.addAttribute("username", user.getUsername());
        return "login";
    }

    @InitBinder
    public void binder(WebDataBinder binder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
        binder.registerCustomEditor(Double.class, new DoubleEditor());
        binder.setFieldDefaultPrefix("u.");
    }
}
