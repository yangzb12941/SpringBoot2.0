package com.jiuxian.service.impl;

import com.jiuxian.annotation.Log;
import com.jiuxian.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-22 11:45:00
 * Comment:
 */
@Service
public class UserServiceImpl implements UserService {

    @Log(value = "test")
    @Override
    public String save(String user) {
        System.out.println("保存用户信息");
        if ("a".equals(user)) {
            throw new RuntimeException();
        }
        return user;
    }

    @Log(value = "test")
    @Override
    public void testAnnotationAop() {
        System.out.println("testAnnotationAop");
    }

    @Override
    public void testIntroduction() {
        System.out.println("do testIntroduction");
    }

    @Override
    public void testAToB() {
        // 外部直接调用 testIntroduction 无Aop方法，而内部需要调用 有Aop 的save方法。
        // 可以使用AopContext.currentProxy() 获取当前代理对象 调用 save 就是 会有增强效果。
        ((UserService)AopContext.currentProxy()).save("yangzb");

        //无增强效果
        this.save("yangzb");
    }
}
