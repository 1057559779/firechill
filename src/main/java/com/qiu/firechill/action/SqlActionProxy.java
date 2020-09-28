package com.qiu.firechill.action;

import com.qiu.firechill.devtest.pojo.QiuUser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author qiu
 * @create 2020/9/28 9:50
 * @Des 代理类
 */
public class SqlActionProxy<T> implements InvocationHandler {

    private Class<T> proxyInterface;

    public SqlActionProxy(Class<T> proxyInterface){
        this.proxyInterface = proxyInterface;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        System.out.println("代理成功");

        return null;
    }

    public T getProxy(){
        return (T) Proxy.newProxyInstance(proxyInterface.getClassLoader(),new Class[]{proxyInterface},this);
    }

}
