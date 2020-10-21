package com.qiu.firechill.action.impl;

import com.qiu.firechill.action.DefaultSelfSqlAction;
import com.qiu.firechill.action.SqlActionProxy;
import java.sql.Connection;

/**
 * @Author qiu
 * @create 2020/10/6 12:46
 * @Des 代理接口的自定义实现类
 */
public class DefaultSqlActionImpl implements DefaultSelfSqlAction {

    @Override
    public Object getInterFace(Class clazz){
        SqlActionProxy sqlActionProxy = new SqlActionProxy(clazz);
        Object proxy = sqlActionProxy.getProxy();
        return proxy;
    }
}
