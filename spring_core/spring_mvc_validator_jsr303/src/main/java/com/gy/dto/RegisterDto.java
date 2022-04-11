package com.gy.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class RegisterDto {
    @NotBlank(message = "{username.empty}")
    private String username;
    @NotNull(message = "{birthday.invalid}")
    @Past(message = "{birthday.invalid}")
    private Date birthday;
    private Double balance;
    @NotBlank(message = "{pwd.empty}")
    private String pwd;
    private String cpwd;
    @Email
    private String email;
    //@Digits(integer = 11, fraction = 0)
    private String phone;
    //@NotEmpty // 集合，映射，数组，字符串大小不能为0
    //@Size(min = 1, max = 3)
    private List<@NotBlank String> hobbies; // 爱好

    private String role; // 角色

    /*@Min(18)
    @Max(60)*/
    //@Range(min = 18, max = 60)
    private int age;

    //@AssertTrue
    public boolean isCheckPwd() {
        return pwd.equals(cpwd);
    }
}
