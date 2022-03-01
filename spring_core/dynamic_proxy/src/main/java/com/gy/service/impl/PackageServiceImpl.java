package com.gy.service.impl;

import com.gy.entity.Package;
import com.gy.service.PackageService;

public class PackageServiceImpl implements PackageService {

    @Override
    public Package getPackage(String code) {
        System.out.println(String.format("取包裹：%s", code));
        if("123".equals(code)) {
            throw new IllegalStateException("ISE");
        }
        Package p = new Package();
        p.setName("包裹1");
        p.setCode(code);
        p.setCotent("一个包裹");
        return p;

    }
}

