package com.gy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IdCard {
    private Integer id;
    private String code; // 身份证号码
}
