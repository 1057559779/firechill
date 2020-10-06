package com.qiu.firechill.action.impl;

import com.qiu.firechill.action.DefaultSelfSqlAction;
import com.qiu.firechill.action.SqlActionProxy;
import java.sql.Connection;

/**
 * @Author VULCAN
 * @create 2020/10/6 12:46
 */
public class DefaultSqlActionImpl implements DefaultSelfSqlAction {

    @Override
    public Object getInterFace(Class clazz){
        SqlActionProxy sqlActionProxy = new SqlActionProxy(clazz);
        Object proxy = sqlActionProxy.getProxy();
        return proxy;
    }
}
