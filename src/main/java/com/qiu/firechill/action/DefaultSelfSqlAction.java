package com.qiu.firechill.action;

/**
 * @Author qiu
 * @create 2020/10/6 12:22
 * @Des 自定义sql处理类
 */
public interface DefaultSelfSqlAction {

    //动态代理，自定义接口
    <E> E getInterFace(Class<E> clazz);
}
