package com.qiu.firechill.session.impl;

import com.qiu.firechill.action.DefaultSelfSqlAction;
import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.action.impl.DefaultSqlActionImpl;
import com.qiu.firechill.action.impl.SimpleSqlActionImpl;
import com.qiu.firechill.session.DbConnectFactory;

import java.sql.*;

/**
 * @Author qiu
 * @create 2020/9/25 14:45
 * @Des 普通方式，用java配置的方式
 */
public class CommonDbConnectFactory implements DbConnectFactory {

    /**
     * 用单例模式让Connection不多次创建
     */
    private  Connection connect;

    public CommonDbConnectFactory(Connection connect) {
        this.connect = connect;
    }

    @Override
    public SqlAction getAction(Class<?> clazz) throws Exception {

        return new SimpleSqlActionImpl(connect,clazz);
    }


    @Override
    public DefaultSelfSqlAction getDefalutSqlAction() {
        return new DefaultSqlActionImpl();
    }

}
