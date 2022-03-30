package com.example.service.impl;

import com.example.annotation.Action;
import com.example.service.PackageService;
import org.springframework.stereotype.Service;

@Service("packageService")
public class PackageServiceImpl implements PackageService {
    // execution(* com.example.service.impl.*.getPackage(String))
    @Action
    @Override
    public boolean getPackage(String name) {
        System.out.println(String.format("获取包裹：%s", name));
        // throw new IllegalStateException("ISE");
        return true;
    }

    @Override
    public boolean sendPackage(String name) {
        System.out.println(String.format("发送包裹：%s", name));
        return true;
//        throw new IllegalArgumentException("IAE");
    }
}
