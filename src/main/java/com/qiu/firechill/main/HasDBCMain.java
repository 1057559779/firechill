package com.qiu.firechill.main;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.sun.org.apache.xml.internal.security.Init;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author qiu
 * @create 2020/9/25 11:18
 * @Des 拥有数据库连接池的jdbc
 */
public class HasDBCMain {
    public static void main(String[] args) throws Exception {
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("url","jdbc:mysql://120.55.88.202:3306/firechildren?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
        map.put("username","root");
        map.put("password","123456");
        map.put("initialSize","5");
        map.put("maxActive","10");
        map.put("maxWait","3000");
        DataSource ds = DruidDataSourceFactory.createDataSource(map);
        Connection conn = ds.getConnection();
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, uname FROM qiu_user");
        //如果有数据，rs.next()返回true
        while(rs.next()){
            System.out.println("用户名："+rs.getString("uname")+" id："+rs.getInt("id"));
        }
        rs.close();
        stmt.close();
        conn.close();
    }
}
