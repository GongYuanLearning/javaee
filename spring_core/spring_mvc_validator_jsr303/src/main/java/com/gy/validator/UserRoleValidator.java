package com.gy.validator;

import com.gy.dto.RegisterDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserRoleValidator implements ConstraintValidator<UserRole, RegisterDto> {
    private static final List<String> ROLES = new ArrayList<>();

    static {
        ROLES.add("ADMIN");
        ROLES.add("CUSTOMER");
    }


    @Override
    public boolean isValid(RegisterDto value, ConstraintValidatorContext context) {
        return ROLES.contains(value.getRole());
    }
}
