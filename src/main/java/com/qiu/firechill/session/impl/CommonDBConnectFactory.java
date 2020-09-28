package com.qiu.firechill.session.impl;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.action.impl.SimpleSqlActionImpl;
import com.qiu.firechill.devtest.config.MyDataSourceConfig;
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

    @Override
    public SqlAction getAction(Class<?> clazz) throws Exception {
        //得到配置信息
        ConnectBean config = new MyDataSourceConfig().config();
        DataSource dataSource = config.getDataSource();
        Connection connect = dataSource.getConnection();

        return new SimpleSqlActionImpl(connect,clazz);
    }

    @Override
    public Object doSelectSql(String sql,Class clazz) throws Exception {
        ConnectBean config = new MyDataSourceConfig().config();
        DataSource dataSource = config.getDataSource();
        Connection conn = dataSource.getConnection();
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        //如果有数据，rs.next()返回true
        while(rs.next()){
            System.out.println("用户名："+rs.getString("uname")+" id："+rs.getInt("id"));
        }
        rs.close();
        stmt.close();
        conn.close();
        return null;
    }
}
