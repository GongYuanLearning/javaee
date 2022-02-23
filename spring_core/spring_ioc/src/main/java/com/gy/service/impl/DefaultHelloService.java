package com.gy.service.impl;

import com.gy.service.HelloService;

public class DefaultHelloService implements HelloService {

    @Override
    public void sayHello(String name) {
        System.out.println(name + ", 你好！");
    }
}
