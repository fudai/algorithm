/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: CglibProxy
 * @package: com.proxy.cglib
 * @date: 2021-11-30 13:56
 * @version: V1.0
 */
package com.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @className: CglibProxy
 * @description:
 * @author: fudai
 * @date: 2021-11-30 13:56
 */
public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 代理方法开始执行");
        Object object = methodProxy.invokeSuper(o,args);
        System.out.println("cglib 代理方法结束执行");
        return object;
    }

    /**
     *
     * @param clazz
     * @return
     */
    public Object createProxyObject(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
