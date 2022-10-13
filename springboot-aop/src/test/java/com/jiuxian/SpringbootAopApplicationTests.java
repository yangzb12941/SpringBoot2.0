package com.jiuxian;

import com.jiuxian.service.DoSthService;
import com.jiuxian.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
//若是不加入这个注解，会导致 没有Aop切面的B方法内部,不能通过 AopContext.currentProxy()
//获取到代理类，也就不能 在B方法内部 调用 有切面注解的A方法。
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class SpringbootAopApplicationTests {

    @Resource
    private UserService userService;

    @Test
    public void testAop() {
        userService.save("张三");
        Assert.assertTrue(true);
    }

    @Test
    public void testAop2() {
        userService.save("a");
    }

    @Test
    public void testAop3() {
        userService.testAnnotationAop();
    }

    @Test
    public void testIntroduction() {
        userService.testIntroduction();
        //Aop 让UserService方法拥有 DoSthService的方法
        DoSthService doSthService = (DoSthService) userService;
        doSthService.doSth();
    }

    /**
     * 测试 同一个类内部 A(无Aop注解)方法直接调用B(Aop注解)，需要用到B方法的Aop增强方式。
     */
    @Test
    public void testAopAToB() {
        userService.testAToB();
        Assert.assertTrue(true);
    }
}

