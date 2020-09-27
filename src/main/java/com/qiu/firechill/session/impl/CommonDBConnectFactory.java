package com.qiu.firechill.session.impl;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.action.impl.SimpleSqlActionImpl;
import com.qiu.firechill.session.ConnectBean;
import com.qiu.firechill.session.DBConnectFactory;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @Author qiu
 * @create 2020/9/25 14:45
 * @Des 普通方式，用java配置的方式
 */
public class CommonDBConnectFactory implements DBConnectFactory {



    public SqlAction getAction(ConnectBean connectBean, Class<?> clazz) throws SQLException {
        DataSource dataSource = connectBean.getDataSource();
        Connection connect = dataSource.getConnection();

        return new SimpleSqlActionImpl(connect,clazz);
    }
}
