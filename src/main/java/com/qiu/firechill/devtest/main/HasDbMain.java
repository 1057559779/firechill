package com.qiu.firechill.devtest.main;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author qiu
 * @create 2020/9/25 11:18
 * @Des 拥有数据库连接池的jdbc
 */
public class HasDbMain {
    public static void main(String[] args) throws Exception {
        int initialCapacity=8;
        Map<String ,Object> map = new HashMap<String, Object>(initialCapacity);
        map.put("url","jdbc:mysql://120.55.88.202:3306/firechildren?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
        map.put("username","root");
        map.put("password","123456");
        map.put("initialSize","5");
        map.put("maxActive","10");
        map.put("maxWait","3000");
        map.put("driverClassName","com.mysql.jdbc.Driver");
        DataSource ds = DruidDataSourceFactory.createDataSource(map);
        Connection connect = ds.getConnection();
        //3.操作数据库，实现增删改查
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, uname FROM qiu_user");
        //如果有数据，rs.next()返回true
        while(rs.next()){
            System.out.println("用户名："+rs.getString("uname")+" id："+rs.getInt("id"));
        }
        rs.close();
        stmt.close();
        connect.close();
    }
}
