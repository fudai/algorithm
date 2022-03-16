/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: Test
 * @package: com.proxy
 * @date: 2021-11-29 19:08
 * @version: V1.0
 */
package com.proxy.jdk;

import com.proxy.cglib.CglibProxy;

import java.lang.reflect.Proxy;

/**
 * @className: Test
 * @description:
 * @author: fudai
 * @date: 2021-11-29 19:08
 */
public class Test {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        CglibProxy cglibProxy = new CglibProxy();
        BizServiceImpl bizService = (BizServiceImpl) cglibProxy.createProxyObject(BizServiceImpl.class);
        bizService.run();
    }

    private static void test1() {
        BizService bizServiceImpl = new BizServiceImpl();
        BizInvocationHandler bizInvocationHandler = new BizInvocationHandler(bizServiceImpl);
        BizService instance = (BizService) bizInvocationHandler.createProxyObject();
        instance.run();
    }

}
