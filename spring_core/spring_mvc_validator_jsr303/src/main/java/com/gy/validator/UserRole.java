package com.gy.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = {UserRoleValidator.class})
public @interface UserRole {
    String message() default "角色必须是ADMIN, CUSTOMER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
