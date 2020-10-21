package com.qiu.firechill.action;

/**
 * @Author qiu
 * @create 2020/10/6 12:22
 * @Des 自定义sql处理类
 */
public interface DefaultSelfSqlAction {

    /**
     * 获得接口的代理实例
     * @param clazz
     * @param <E>
     * @return
     */
    <E> E getInterFace(Class<E> clazz);
}
