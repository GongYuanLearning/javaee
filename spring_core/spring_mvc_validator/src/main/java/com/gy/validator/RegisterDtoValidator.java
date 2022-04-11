package com.gy.validator;

import com.gy.dto.RegisterDto;
import com.gy.model.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("registerDtoValidator")
public class RegisterDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        // isAssignableFrom，参数中的类是不是调用这个方法的类的子类（或者本身）
        return RegisterDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterDto obj = (RegisterDto) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username",
                "username.empty", "username不能为空");

        if(!StringUtils.hasText(obj.getPhone())) {
            errors.rejectValue("phone",
                    "phone.empty",
                    "phone不能为空");
        } else if (!obj.getPhone()
                .matches("1\\d{10}")) {
            errors.rejectValue("phone",
                    "phone.invalid",
                    "phone应该是11位数字");
        }
        if(!StringUtils.hasText(obj.getPwd())) {
            errors.rejectValue("pwd",
                    "pwd.empty",
                    "pwd不能为空");
        } else if (obj.getPwd().length() < 6) {
            errors.rejectValue("pwd",
                    "pwd.invalid",
                    "pwd至少是6位");
        }

        if(!obj.getPwd().equals(obj.getCpwd())) {
            errors.reject("pwd.not.match", "密码和确认密码不匹配！");
        }
    }
}

