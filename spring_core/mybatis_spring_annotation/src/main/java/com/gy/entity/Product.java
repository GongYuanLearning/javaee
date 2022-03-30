package com.gy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private List<Order> orders;
}
