package com.gy.entity;

import lombok.Data;

@Data
public class BookCriteria {
    private String name;
    private int minPrice;
    private int maxPrice;
}
