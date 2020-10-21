package com.qiu.firechill.session.impl;

import com.qiu.firechill.action.CudSqlAction;
import com.qiu.firechill.action.DefaultSelfSqlAction;
import com.qiu.firechill.action.ReadSqlAction;
import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.action.impl.CudSqlActionImpl;
import com.qiu.firechill.action.impl.DefaultSqlActionImpl;
import com.qiu.firechill.action.impl.ReadSqlActionImpl;
import com.qiu.firechill.action.impl.SimpleSqlActionImpl;
import com.qiu.firechill.devtest.config.MyDataSourceConfig;
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
    private static Connection connect;

    static {
        try {
            //得到配置信息
            connect = new MyDataSourceConfig().config().getDataSource().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SqlAction getAction(Class<?> clazz) throws Exception {

        return new SimpleSqlActionImpl(connect,clazz);
    }

    @Override
    public CudSqlAction getCudSqlAction(Class<?> clazz) throws Exception {

        return new CudSqlActionImpl(connect,clazz);
    }

    @Override
    public ReadSqlAction getReadSqlAction(Class<?> clazz) throws Exception {

        return new ReadSqlActionImpl(connect,clazz);
    }

    @Override
    public DefaultSelfSqlAction getDefalutSqlAction() {
        return new DefaultSqlActionImpl();
    }

}
