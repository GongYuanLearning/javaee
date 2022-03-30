package com.gy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Order {
    private Integer id;
    private  String ordersn;
    private List<Product> products;
}
