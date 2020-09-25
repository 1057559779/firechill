package com.qiu.firechill.action.impl;

import com.qiu.firechill.action.SqlAction;
import com.qiu.firechill.devtest.pojo.QiuUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Author VULCAN
 * @create 2020/9/25 16:12
 */
public class SqlActionImpl<T> implements SqlAction {

    private Connection connect;

    public SqlActionImpl() {

    }

    public SqlActionImpl(Connection connect) {
        this.connect=connect;
    }

    public T selectById() throws SQLException {

        return null;
    }

    public List<T> selectAll() throws SQLException {

        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, uname FROM qiu_user");

        while(rs.next()){
            System.out.println("用户名："+rs.getString("uname")+" id："+rs.getInt("id"));
        }
        rs.close();
        stmt.close();
        connect.close();
        return null;
    }
}
