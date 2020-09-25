package com.qiu.firechill.session.impl;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.action.impl.SqlActionImpl;
import com.qiu.firechill.session.DBConnectFactory;
import com.qiu.firechill.session.DataSourceInfoConfig;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;

/**
 * @Author qiu
 * @create 2020/9/25 14:45
 * @Des 常用方式，用java配置的方式
 */
public class CommonDBConnectFactory implements DBConnectFactory {

    public SqlAction getAction(DataSource dataSource) throws SQLException {
        Connection connect = dataSource.getConnection();

        return new SqlActionImpl(connect);
    }
}
