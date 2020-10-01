package com.qiu.firechill.session.impl;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.action.impl.SimpleSqlActionImpl;
import com.qiu.firechill.common.sql.GenerateSelectSql;
import com.qiu.firechill.common.sql.impl.GenerateSelectSqlImpl;
import com.qiu.firechill.devtest.config.MyDataSourceConfig;
import com.qiu.firechill.session.ConnectBean;
import com.qiu.firechill.session.DBConnectFactory;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

/**
 * @Author qiu
 * @create 2020/9/25 14:45
 * @Des 普通方式，用java配置的方式
 */
public class CommonDBConnectFactory implements DBConnectFactory {


    @Override
    public SqlAction getAction(Class<?> clazz) throws Exception {
        //得到配置信息
        ConnectBean config = new MyDataSourceConfig().config();
        DataSource dataSource = config.getDataSource();
        Connection connect = dataSource.getConnection();

        return new SimpleSqlActionImpl(connect,clazz);
    }

}
