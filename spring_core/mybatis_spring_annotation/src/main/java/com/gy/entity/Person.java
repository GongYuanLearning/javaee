package com.gy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private Integer age;
    private IdCard card; //个人身份证关联
}
