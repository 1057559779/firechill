package com.qiu.firechill.action;

import com.qiu.firechill.ann.Delete;
import com.qiu.firechill.ann.Insert;
import com.qiu.firechill.ann.Select;
import com.qiu.firechill.ann.Update;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
        Annotation[] allann = method.getDeclaredAnnotations();
        for (Annotation annotation:allann) {
            String name = annotation.annotationType().getSimpleName();
            switch (name){
                case "Select":
                    System.out.println("select");
                    break;
                case "Update":
                    System.out.println("update");
                    break;
                case "Delete":
                    System.out.println("delete");
                    break;
                case "Insert":
                    System.out.println("insert");
                    break;
            }
        }

        return null;
    }

    public T getProxy(){
        return (T) Proxy.newProxyInstance(proxyInterface.getClassLoader(),new Class[]{proxyInterface},this);
    }

}
