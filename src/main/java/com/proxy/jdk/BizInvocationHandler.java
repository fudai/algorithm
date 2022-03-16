/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: BizInvocationHandler
 * @package: com.proxy
 * @date: 2021-11-29 19:04
 * @version: V1.0
 */
package com.proxy.jdk;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @className: BizInvocationHandler
 * @description:
 * @author: fudai
 * @date: 2021-11-29 19:04
 */
public class BizInvocationHandler implements InvocationHandler {

    private Object bizService;

    public BizInvocationHandler(Object bizService) {
        this.bizService = bizService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("代理方法开始执行");

        method.invoke(bizService,args);

        System.out.println("代理方法结束执行");

        return null;
    }

    public Object createProxyObject() {
        return Proxy.newProxyInstance(bizService.getClass().getClassLoader(), bizService.getClass().getInterfaces(), this);
    }
}
